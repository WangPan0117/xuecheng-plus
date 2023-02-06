package com.xuecheng.content.service.impl;

import com.xuecheng.content.mapper.CourseCategoryMapper;
import com.xuecheng.content.model.dto.CourseCategoryTreeDto;
import com.xuecheng.content.service.CourseCategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Slf4j
@Service
public class CourseCategoryServiceImpl implements CourseCategoryService {

    @Autowired
    CourseCategoryMapper courseCategoryMapper;

    @Override
    public List<CourseCategoryTreeDto> queryTreeNodes(String id) {
        // 得到了根节点下的所有子节点。
        List<CourseCategoryTreeDto> courseCategoryTreeDtos = courseCategoryMapper.selectTreeNodes(id);
        // 定义一个 List作最终返回的数据
        List<CourseCategoryTreeDto> categoryTreeDtoList = new ArrayList<>();
        HashMap<String,CourseCategoryTreeDto> nodeMap = new HashMap();
        // 将数据封装到List中，只包括了根节点的下属节点。
        courseCategoryTreeDtos.stream().forEach(item->{
            nodeMap.put(item.getId(), item);
            if (item.getParentid().equals(id)) {
                categoryTreeDtoList.add(item);
            }
            // 找到子节点，放到它的父节点
            String parentid = item.getParentid();
            // 找到该节点的父节点对象
            CourseCategoryTreeDto parentNode = nodeMap.get(parentid);
            if (parentNode != null) {
                List childrenTreeNodes = parentNode.getChildrenTreeNodes();
                if (childrenTreeNodes == null) {
                    parentNode.setChildrenTreeNodes(new ArrayList<CourseCategoryTreeDto>());
                }
                parentNode.getChildrenTreeNodes().add(item);
            }
        });
        // 返回的 List 中只包括根节点的直接下属节点
        return categoryTreeDtoList;
    }
}
