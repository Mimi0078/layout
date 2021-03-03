import java.util.ArrayList;
import java.util.List;

public class NavigationItem {

    private Long id;
    private String name;
    private Long parentId;
    private Boolean isHidden;
    private String linkURL;
    private List<NavigationItem> childItems;

    public NavigationItem(Long id, String name, Long parentId, boolean isHidden, String linkURL) {
        this.id = id;
        this.name = name;
        this.parentId = parentId;
        this.isHidden = isHidden;
        this.linkURL = linkURL;
        childItems = new ArrayList<>();
    }


    public void print(int level){
        if(!this.isHidden) {
            System.out.print(".".repeat(level * 2));
            System.out.println(" " + this.name);
        }
        if(this.getChildItems().size()>0){
            this.getChildItems().forEach(it->it.print(level+1));
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public boolean isHidden() {
        return isHidden;
    }

    public void setHidden(boolean hidden) {
        isHidden = hidden;
    }

    public String getLinkURL() {
        return linkURL;
    }

    public void setLinkURL(String linkURL) {
        this.linkURL = linkURL;
    }

    public void addChildItem(NavigationItem item){
        childItems.add(item);
    }

    public List<NavigationItem> getChildItems() {
        return childItems;
    }
}
