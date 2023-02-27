package com.xuecheng.content.service;

import com.xuecheng.content.model.dto.TeachPlanDto;

import java.util.List;

public interface TeachplanService {
    List<TeachPlanDto> findTeachplayTree(long courseId);
}
