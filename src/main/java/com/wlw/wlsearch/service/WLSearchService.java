package com.wlw.wlsearch.service;

import com.wlw.wlsearch.dto.SearchParam;
import com.wlw.wlsearch.entity.SearchResult;
import org.springframework.data.domain.Page;

/**
 * @author fuxg
 * @create 2017-01-23 15:22
 */
public interface WLSearchService {

    Page<SearchResult> search(SearchParam searchParam);

}
