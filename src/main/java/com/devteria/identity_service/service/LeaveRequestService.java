package com.devteria.identity_service.service;

import com.devteria.identity_service.dto.request.LeaveRequestRequest;
import com.devteria.identity_service.dto.response.LeaveRequestResponse;
import com.devteria.identity_service.entity.LeaveRequest;
import com.devteria.identity_service.exception.AppException;
import com.devteria.identity_service.exception.ErrorCode;
import com.devteria.identity_service.mapper.LeaveRequestMapper;
import com.devteria.identity_service.repository.LeaveRequestRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class LeaveRequestService {
    LeaveRequestRepository leaveRequestRepository;
    LeaveRequestMapper leaveRequestMapper;
    EmployeeService employeeService;
    LeaveTypeService leaveTypeService;

    public LeaveRequestResponse createLeaveRequest(LeaveRequestRequest request){
        if (!leaveTypeService.existedById(request.getLeaveType().getId())
                || !employeeService.existedById(request.getEmployee().getId()))
        {
            throw new AppException(ErrorCode.CANNOT_CREATE_LEAVE_REQUEST);
        }
        LeaveRequest leaveRequest = leaveRequestMapper.toLeaveRequest(request);
        leaveRequest = leaveRequestRepository.save(leaveRequest);
        return  leaveRequestMapper.toLeaveRequestResponse(leaveRequest);
    }
}
