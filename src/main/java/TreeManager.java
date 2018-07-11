import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.function.Predicate;

public class TreeManager {

    private TreeRepository treeRepository;

    public TreeManager(TreeRepository treeRepository) {
        this.treeRepository = treeRepository;
    }

    void addNode(AbstractNode newNode) {
        treeRepository.save(newNode);
    }

    <T extends AbstractNode> T getTree(T root) {
        List<T> subNodes = getSubNodes(root);
        root.setSubNodes(subNodes);
        return root;
    }

    <T extends AbstractNode> List<T> getSubNodes(T root) {
        List<T> subNodes = treeRepository.findByParentNode(root);
        if (subNodes != null && !subNodes.isEmpty()) {
            for (T subNode : subNodes) {
                subNode.setSubNodes(getSubNodes(subNode));
            }
        }
        return subNodes;
    }

    <T extends AbstractNode>  Boolean anyMatch(T node, Predicate<T> predicate) {
        boolean result = predicate.test(node);
        if (result) {
            return true;
        } else {
            List<T> subNodes = node.getSubNodes();
            if (subNodes != null && !subNodes.isEmpty()) {
                for (T subNode : subNodes) {
                    if (anyMatch(subNode, predicate)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    <T extends AbstractNode> void filter(T node, Predicate<T> predicate) {
        if (anyMatch(node, predicate)) {
            List<T> subNodes = node.getSubNodes();
            if (subNodes != null && !subNodes.isEmpty()) {
                Iterator<T> iterator = subNodes.iterator();
                while (iterator.hasNext()) {
                    T next = iterator.next();
                    if (anyMatch(next, predicate)) {
                        filter(next, predicate);
                    } else {
                        iterator.remove();
                    }
                }
            }
        } else {
            node.setSubNodes(Collections.emptyList());
        }
    }
}
