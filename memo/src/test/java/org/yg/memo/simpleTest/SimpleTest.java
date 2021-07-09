package org.yg.memo.simpleTest;

import org.junit.jupiter.api.Test;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

public class SimpleTest {
    @Test
    public void testSearchPage(){
        Pageable pageable = PageRequest.of(0, 10, Sort.by("bno").descending().and(Sort.by("title").ascending()));
        System.out.println(pageable.getOffset());
        System.out.println(pageable.getSort());

        Sort sort = pageable.getSort();
        sort.stream().forEach(a->System.out.println(a));

        //Page<Object[]> result = boardRepository.searchPage("t", "1", pageable);

    }

}
