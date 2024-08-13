package com.test.auctionservice.service;

import com.test.auctionservice.common.SlotStatusEnum;
import com.test.auctionservice.dto.AddSlotRequest;
import com.test.auctionservice.dto.AuctionSlotResponse;
import com.test.auctionservice.entity.AuctionSlot;
import com.test.auctionservice.exception.AuctionServiceException;
import com.test.auctionservice.exception.ResourceNotFoundException;
import com.test.auctionservice.repository.AuctionSlotRepository;
import com.test.auctionservice.common.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class AuctionSlotServiceImpl implements AuctionSlotService {

    @Autowired
    private AuctionSlotRepository auctionSlotRepository;

    @Override
    public void addAuctionSlot(AddSlotRequest addSlotRequest) {
        AuctionSlot auctionSlot = AuctionSlot.builder()
                .startTime(addSlotRequest.getStartTime())
                .endTime(addSlotRequest.getEndTime())
                .status(SlotStatusEnum.CLOSE)
                .build();

        auctionSlotRepository.save(auctionSlot);
    }

    @Override
    public void editAuctionSlot(Long slotId, AddSlotRequest addSlotRequest) throws ResourceNotFoundException {
        Optional<AuctionSlot> auctionSlot = auctionSlotRepository.findById(slotId);
        AuctionSlot auctionSlotValue = auctionSlot.orElseThrow(()-> new ResourceNotFoundException(String.format(Constants.AUCTION_SLOT_NOT_FOUND_FOR_SLOT_ID, slotId)));
        checkAndUpateAuctionSlot(addSlotRequest, auctionSlotValue);
        auctionSlotRepository.save(auctionSlotValue);
    }

    @Override
    public AuctionSlotResponse getAuctionSlot(Long slotId) throws AuctionServiceException {
        Optional<AuctionSlot> auctionSlot = auctionSlotRepository.findById(slotId);
        AuctionSlot auctionSlotValue = auctionSlot.orElseThrow(()-> new ResourceNotFoundException(String.format(Constants.AUCTION_SLOT_NOT_FOUND_FOR_SLOT_ID, slotId)));
        return createAuctionSlotResponse(auctionSlotValue);
    }

    @Override
    public List<AuctionSlotResponse> getAllAuctionSlots() {
        List<AuctionSlot> auctionSlots = auctionSlotRepository.findAll();
        return auctionSlots.stream().map(this::createAuctionSlotResponse).toList();
    }

    @Override
    public void deleteAuctionSlot(Long slotId) throws AuctionServiceException {
        Optional<AuctionSlot> auctionSlot = auctionSlotRepository.findById(slotId);
        if(auctionSlot.isEmpty()){
           throw new ResourceNotFoundException(String.format(Constants.AUCTION_SLOT_NOT_FOUND_FOR_SLOT_ID, slotId));
        }
        auctionSlotRepository.deleteById(slotId);
    }

    @Override
    public List<AuctionSlot> getOpenAuctionSlots() {
        return auctionSlotRepository.findOpenSlots(LocalDateTime.now(), SlotStatusEnum.CLOSE);
    }

    @Override
    public List<AuctionSlot> getEndedAuctionSlots() {
        return auctionSlotRepository.findEndedSlots(LocalDateTime.now(), SlotStatusEnum.OPEN);
    }

    private AuctionSlotResponse createAuctionSlotResponse(AuctionSlot auctionSlot){
        return new AuctionSlotResponse(auctionSlot.getStartTime(), auctionSlot.getEndTime(), auctionSlot.getStatus(), auctionSlot.getSlotId());
    }

    private void checkAndUpateAuctionSlot(AddSlotRequest addSlotRequest, AuctionSlot auctionSlot){
        if(Objects.nonNull(addSlotRequest.getStartTime())){
            auctionSlot.setStartTime(addSlotRequest.getStartTime());
        }
        if(Objects.nonNull(addSlotRequest.getEndTime())){
            auctionSlot.setEndTime(addSlotRequest.getEndTime());
        }
    }

}
