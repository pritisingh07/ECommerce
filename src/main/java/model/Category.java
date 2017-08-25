package model;

import io.swagger.annotations.ApiModelProperty;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Map;

@Document(collection = "categories")
public class Category {

    @Id
    @ApiModelProperty(notes = "The database generated product ID")
    private String id;

    @NotNull(message = "Id can't be null")
    @ApiModelProperty(notes = "Category id", required = true)
    private String categoryId;

    @NotNull(message = "Name can't be null")
    @ApiModelProperty(notes = "Name in differene locales", required = true)
    private Map<String, String> name;

    private String parent;

    @ApiModelProperty(notes = "Category hierarchy ")
    private List<Object> ancestors;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public Map<String, String> getName() {
        return name;
    }

    public void setName(Map<String, String> name) {
        this.name = name;
    }

    public String getParent() {
        return parent;
    }

    public void setParent(String parent) {
        this.parent = parent;
    }

    public List<Object> getAncestors() {
        return ancestors;
    }

    public void setAncestors(List<Object> ancestors) {
        this.ancestors = ancestors;
    }

    @Override
    public String toString() {
        return "id = " + id + "\n" +
                "categoryId = " + categoryId + "\n" +
                "nameMap = " + name.toString() + "\n" +
                "parent = " + parent + "\n" +
                "ancestorsList = " + ancestors.toString();
    }
}
