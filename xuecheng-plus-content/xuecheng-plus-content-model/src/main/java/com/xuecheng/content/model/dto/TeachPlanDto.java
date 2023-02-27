package com.xuecheng.content.model.dto;

import com.xuecheng.content.model.po.Teachplan;
import com.xuecheng.content.model.po.TeachplanMedia;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
public class TeachPlanDto extends Teachplan {

    // 子课程目录
    List<TeachPlanDto> teachPlanTreeNodes;
    // 关联的媒资信息
    TeachplanMedia teachplanMedia;


}
