package com.dy.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dy.model.entity.Question;
import com.dy.service.QuestionService;
import com.dy.mapper.QuestionMapper;
import org.springframework.stereotype.Service;

/**
* @author 微光
* @description 针对表【question(帖子)】的数据库操作Service实现
* @createDate 2024-07-14 10:24:31
*/
@Service
public class QuestionServiceImpl extends ServiceImpl<QuestionMapper, Question>
    implements QuestionService{

}




