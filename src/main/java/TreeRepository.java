import java.util.List;

public interface TreeRepository {

    <T extends AbstractNode>  void save(T abstractNode);

    <T extends AbstractNode> List<T> findByParentNode(T node);
}
