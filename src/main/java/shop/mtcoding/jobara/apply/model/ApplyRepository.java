package shop.mtcoding.jobara.apply.model;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ApplyRepository {
    
    public List<Apply> findAll();

    public Apply findById(int id);

    public int insert(Apply apply);

    public int updateById(Apply apply);

    public int deleteById(int id);

    public Apply findByUserIdAndBoardId(Apply apply);
}