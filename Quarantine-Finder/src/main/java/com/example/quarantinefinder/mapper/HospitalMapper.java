package com.example.quarantinefinder.mapper;

import com.example.quarantinefinder.constant.AbstractMapper;
import com.example.quarantinefinder.entity.Hospital;
import com.example.quarantinefinder.request.HospitalRequest;
import com.example.quarantinefinder.response.HospitalResponse;

public class HospitalMapper implements AbstractMapper<Hospital, HospitalRequest, HospitalResponse> {
    @Override
    public Hospital toEntity(HospitalRequest request) {
        Hospital hospital = new Hospital();
        hospital.setName(request.getName());
        hospital.setAddress(request.getAddress());
        return hospital;
    }

    @Override
    public void toEntity(HospitalRequest request, Hospital entity) {

    }

    @Override
    public HospitalResponse toBasicResponse(Hospital entity) {
        HospitalResponse hospitalResponse = new HospitalResponse();
        hospitalResponse.setName(entity.getName());
        hospitalResponse.setAddress(entity.getAddress());
        return hospitalResponse;
    }
}
