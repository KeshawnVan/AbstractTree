import java.util.List;

public class AbstractNode<ID> {

    protected ID id;

    protected ID parentId;

    protected List<? extends AbstractNode> subNodes;

    public ID getId() {
        return id;
    }

    public void setId(ID id) {
        this.id = id;
    }

    public ID getParentId() {
        return parentId;
    }

    public void setParentId(ID parentId) {
        this.parentId = parentId;
    }

    public List<? extends AbstractNode> getSubNodes() {
        return subNodes;
    }

    public void setSubNodes(List<? extends AbstractNode> subNodes) {
        this.subNodes = subNodes;
    }

    @Override
    public String toString() {
        return "AbstractNode{" +
                "id=" + id +
                ", parentId=" + parentId +
                '}';
    }
}
