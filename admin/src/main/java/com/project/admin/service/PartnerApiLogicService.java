package com.project.admin.service;

import com.project.admin.model.entity.Partner;
import com.project.admin.model.network.Header;
import com.project.admin.model.network.Pagination;
import com.project.admin.model.network.request.PartnerApiRequest;
import com.project.admin.model.network.response.PartnerApiResponse;
import com.project.admin.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PartnerApiLogicService extends BaseService<PartnerApiRequest, PartnerApiResponse, Partner> {

    private final CategoryRepository categoryRepository;
    @Override
    public Header<PartnerApiResponse> create(Header<PartnerApiRequest> request) {
        PartnerApiRequest body = request.getData();
        Partner partner = Partner.builder()
                .name(body.getName())
                .status(body.getStatus())
                .callCenter(body.getCallCenter())
                .partnerNumber(body.getPartnerNumber())
                .businessNumber(body.getBusinessNumber())
                .ceoName(body.getCeoName())
                .registeredAt(LocalDateTime.now())
                .category(categoryRepository.getOne(body.getCategoryId()))
                .build();
        Partner newPartner = baseRepository.save(partner);
        return Header.OK(response(newPartner));
    }

    @Override
    public Header<PartnerApiResponse> read(Long id) {
        return baseRepository.findById(id)
                .map(partner -> Header.OK(response(partner)))
                .orElseGet(() -> Header.ERROR("데이터 없음"));
    }

    @Override
    public Header<PartnerApiResponse> update(Header<PartnerApiRequest> request) {
        PartnerApiRequest body = request.getData();
        return baseRepository.findById(body.getId())
                .map( entity -> {
                    entity.setName(body.getName())
                            .setStatus(body.getStatus())
                            .setCallCenter(body.getCallCenter())
                            .setPartnerNumber(body.getPartnerNumber())
                            .setBusinessNumber(body.getBusinessNumber())
                            .setCeoName(body.getCeoName())
                            .setRegisteredAt(body.getRegisteredAt())
                            .setUnregisteredAt(body.getUnRegisteredAt())
                            .setCategory(categoryRepository.getOne(body.getCategoryId()));

                    return entity;
                })
                .map( newEntity -> baseRepository.save(newEntity))
                .map( partner -> Header.OK(response(partner)))
                .orElseGet(() -> Header.ERROR("데이터 없음"));
    }

    @Override
    public Header delete(Long id) {
        return baseRepository.findById(id)
                .map(partner -> {
                    baseRepository.delete(partner);
                    return Header.OK();
                })
                .orElseGet(()->Header.ERROR("데이터 없음"));
    }

    private PartnerApiResponse response(Partner partner){
        PartnerApiResponse partnerApiResponse = PartnerApiResponse.builder()
                .id(partner.getId())
                .name(partner.getName())
                .status(partner.getStatus())
                .callCenter(partner.getCallCenter())
                .partnerNumber(partner.getPartnerNumber())
                .businessNumber(partner.getBusinessNumber())
                .ceoName(partner.getCeoName())
                .registeredAt(partner.getRegisteredAt())
                .unRegisteredAt(partner.getUnregisteredAt())
                .categoryId(partner.getCategory().getId())
                .build();

        return partnerApiResponse;
    }

    @Override
    public Header<List<PartnerApiResponse>> search(Pageable pageable){
        
        Page<Partner> partners = baseRepository.findAll(pageable);
        List<PartnerApiResponse> partnerApiResponseList = partners.stream()
                .map( partner -> response(partner))
                .collect(Collectors.toList()); //자료형 변환

        Pagination pagination = Pagination.builder()
                .totalPages(partners.getTotalPages())
                .totalElement(partners.getTotalElements())
                .currentPage(partners.getNumber())
                .currentElement(partners.getNumberOfElements())
                .build();

        // list<UserApiResponse> -> header<list<UserApiResponse>>
        return Header.OK(partnerApiResponseList,pagination);
    }
}
