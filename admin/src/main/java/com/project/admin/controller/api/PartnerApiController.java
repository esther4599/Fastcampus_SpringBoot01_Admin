package com.project.admin.controller.api;

import com.project.admin.controller.CRUDController;
import com.project.admin.model.entity.Partner;
import com.project.admin.model.network.request.PartnerApiRequest;
import com.project.admin.model.network.response.PartnerApiResponse;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/partner")
public class PartnerApiController extends CRUDController<PartnerApiRequest, PartnerApiResponse, Partner> {

}
