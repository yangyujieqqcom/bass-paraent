package com.funo.dao;
import com.funo.enity.Wares;
import com.funo.service.WareServiceImp;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import java.util.List;
@Mapper
public interface WareMapper {

    @Select("select * from wares")
    public List<Wares> findAll();
    @Insert("update wares set number=#{id}")
    public  void update(int id);








}
