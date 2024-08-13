package com.test.auctionservice.common;

import com.test.auctionservice.dto.AddSlotRequest;
import com.test.auctionservice.exception.AuctionServiceException;
import com.test.auctionservice.exception.BadRequestException;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
import java.util.Objects;

import static com.test.auctionservice.common.Constants.MANDATORY_PARAMETERS_MISSING_FROM_ADD_SLOT_REQUEST;
import static com.test.auctionservice.common.Constants.START_TIME_CANNOT_BE_GREATER_THAN_END_TIME;

@Slf4j
public class AuctionServiceValidationUtil {

    private AuctionServiceValidationUtil() {
        throw new IllegalStateException("Utility class");
    }

    public static void validateAddSlotRequest(AddSlotRequest addSlotRequest) throws AuctionServiceException {
        LocalDateTime startTime = addSlotRequest.getStartTime();
        LocalDateTime endTime = addSlotRequest.getEndTime();

        if (Objects.isNull(startTime) || Objects.isNull(endTime)) {
            log.error("Mandatory parameters: {startTime, Endtime} missing");
            throw new BadRequestException(MANDATORY_PARAMETERS_MISSING_FROM_ADD_SLOT_REQUEST);
        }
        if (startTime.isAfter(endTime)) {
            log.error("startTime cannot be greater than endTime");
            throw new BadRequestException(START_TIME_CANNOT_BE_GREATER_THAN_END_TIME);
        }

    }
}
