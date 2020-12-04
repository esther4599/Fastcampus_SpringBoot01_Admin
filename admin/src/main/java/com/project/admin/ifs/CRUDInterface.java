package com.project.admin.ifs;

import com.project.admin.model.network.Header;
import org.springframework.data.domain.Pageable;

import java.util.List;

//요청과 응답을 generic으로 설정한다.
public interface CRUDInterface <Req,Res> {

    Header<Res> create(Header<Req> request); //todo request object는 추후에 추가 => generic으로 추가 완료

    Header<Res> read(Long id);

    Header<Res> update(Header<Req> request);

    Header delete(Long id);

    Header<List<Res>> search(Pageable pageable);
}
