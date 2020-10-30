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
 * @since 2020-10-27
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

    private String objectOneName;

    private String objectOneAttribute;

    private String objectOneImageUrl;

    private String objectOneVedioUrl;

    private String objectTwoName;

    private String objectTwoAttribute;

    private String objectTwoImageUrl;

    private String objectTwoVedioUrl;

    private String oneRelationTwo;

    private Integer isBuildFlag;


}
