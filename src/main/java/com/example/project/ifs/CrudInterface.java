package com.example.project.ifs;

import com.example.project.model.network.Header;

public interface CrudInterface<Req, Res> {
    Header<Res> create(Header<Req> request) throws Exception;

    Header<Res> read(Long id);

    Header<Res> update(Header<Req> request);

    Header<Res> delete(Long id);
}
