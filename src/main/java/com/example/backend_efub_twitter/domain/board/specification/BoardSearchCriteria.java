package com.example.backend_efub_twitter.domain.board.specification;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BoardSearchCriteria {
    private UUID userId;
    private List<String> hashTagKeyword;
}
