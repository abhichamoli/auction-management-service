package com.test.auctionservice.common;

import com.test.auctionservice.entity.AuctionSlot;
import com.test.auctionservice.entity.ProductAuction;
import com.test.auctionservice.kafka.KafkaProducer;
import com.test.auctionservice.kafka.KafkaWinMessage;
import com.test.auctionservice.repository.AuctionSlotRepository;
import com.test.auctionservice.repository.ProductAuctionRepository;
import com.test.auctionservice.service.AuctionService;
import com.test.auctionservice.service.AuctionSlotService;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Slf4j
public class AuctionScheduler {

    @Autowired
    private AuctionService auctionService;

    @Autowired
    private AuctionSlotService auctionSlotService;

    @Autowired
    private ProductAuctionRepository productAuctionRepository;

    @Autowired
    private KafkaProducer kafkaProducer;

    @Autowired
    private AuctionServiceConfigReader auctionServiceConfigReader;

    @Autowired
    private AuctionSlotRepository auctionSlotRepository;

    @Scheduled(fixedRate = 30000)
    @Transactional
    public void checkAuctionSlotStartTime() {
        log.info("Scheduler is running for checking Auction Slot start times");
        List<AuctionSlot> auctionSlots = auctionSlotService.getOpenAuctionSlots();
        log.info("Active auction slots: {}", auctionSlots);

        auctionSlots.forEach(slot -> {
            slot.setStatus(SlotStatusEnum.OPEN);
            List<ProductAuction> auctions = slot.getProductAuctions();
            auctions.forEach(auction -> {
                auction.setStatus(ProductAuctionStatusEnum.ACTIVE);
            });
        });
        auctionSlotRepository.saveAll(auctionSlots);
    }

    @Scheduled(fixedRate = 30000)
    @Transactional
    public void checkForWinners(){
        log.info("Schduler is running for checking winners");
        List<String> winners = new ArrayList<>();
        List<ProductAuction>  productAuctions = auctionService.findEndedAuctions();

        productAuctions.forEach(productAuction -> {
            productAuction.setStatus(ProductAuctionStatusEnum.COMPLETED);
            winners.add(productAuction.getHighestBidderId());
            productAuctionRepository.save(productAuction);

            KafkaWinMessage kafkaWinMessage = AuctionServiceHelper.createKafkaWinMessage(productAuction);

            kafkaProducer.sendMessage(auctionServiceConfigReader.getKafkaTopic(), kafkaWinMessage);

        });
        log.info("winners List: {}", winners);
    }

    @Scheduled(fixedRate = 30000)
    @Transactional
    public void checkAuctionSlotEndTimes(){
        log.info("Schduler is running for checking Auction Slot End Times");
        List<AuctionSlot> auctionSlots = auctionSlotService.getEndedAuctionSlots();
        log.info("Ended auction slots: {}", auctionSlots);

        auctionSlots.forEach(slot ->{
            slot.setStatus(SlotStatusEnum.EXPIRED);
            List<ProductAuction> productAuctions = slot.getProductAuctions();
            productAuctions.forEach(auction ->{
                auction.setStatus(ProductAuctionStatusEnum.ENDED);
            });
        });

        auctionSlotRepository.saveAll(auctionSlots);
    }

}
