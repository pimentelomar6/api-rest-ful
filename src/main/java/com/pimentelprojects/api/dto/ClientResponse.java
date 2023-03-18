package com.pimentelprojects.api.dto;

import com.pimentelprojects.api.models.Client;
import lombok.Data;

import java.util.List;

@Data
public class ClientResponse {
    private List<Client> content;
    private Integer pageNumber;
    private Integer pageSize;
    private Integer totalElements;
    private Integer totalPages;
    private Boolean last;
}
