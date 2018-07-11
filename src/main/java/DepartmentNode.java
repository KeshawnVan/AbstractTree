public class DepartmentNode extends AbstractNode<String>{

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "DepartmentNode{" +
                "name='" + name + '\'' +
                ", id=" + id +
                ", parentId=" + parentId +
                '}';
    }
}
