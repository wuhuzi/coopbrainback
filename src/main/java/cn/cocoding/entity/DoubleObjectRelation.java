package cn.cocoding.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author wuhuzi
 * @since 2020-10-30
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("cbb_double_object_relation")
public class DoubleObjectRelation implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private Integer userId;

    private String objectOneLableName;

    private String objectOneName;

    private String objectOneAttribute;

    private String objectOneFileType;

    private String objectOneFileUrl;

    private String objectTwoLableName;

    private String objectTwoName;

    private String objectTwoAttribute;

    private String objectTwoFileType;

    private String objectTwoFileUrl;

    private String oneRelationTwo;

    private String oneRelationTwoAttribute;

    private Integer isBuildFlag;

    private String remark;

    private Integer delete;


}
