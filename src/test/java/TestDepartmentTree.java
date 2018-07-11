import org.junit.jupiter.api.Test;

import java.util.function.Predicate;

public class TestDepartmentTree {

    @Test
    public void testNode() {
        DepartmentNode d1 = new DepartmentNode();
        d1.setId("1");
        d1.setParentId("root");
        d1.setName("StarTimes");
        System.out.println(d1);

        DepartmentNode d2 = new DepartmentNode();
        d2.setId("2");
        d2.setParentId("1");
        d2.setName("21");

        DepartmentNode d3 = new DepartmentNode();
        d3.setId("3");
        d3.setParentId("1");
        d3.setName("22");

        DepartmentNode d4 = new DepartmentNode();
        d4.setId("4");
        d4.setParentId("2");
        d4.setName("24");

        DepartmentNode d5 = new DepartmentNode();
        d5.setName("31");
        d5.setParentId("4");
        d5.setId("5");

        DepartmentNode d6 = new DepartmentNode();
        d6.setName("23");
        d6.setParentId("1");
        d6.setId("6");

        TreeRepository memoryTreeRepository = new MemoryTreeRepository();

        TreeManager treeManager = new TreeManager(memoryTreeRepository);

        treeManager.addNode(d1);
        treeManager.addNode(d2);
        treeManager.addNode(d3);
        treeManager.addNode(d4);
        treeManager.addNode(d5);
        treeManager.addNode(d6);
        DepartmentNode tree = treeManager.getTree(d1);
        System.out.println(tree);

        Boolean filter = treeManager.anyMatch(d1, node -> node.getName().equals("31"));
        System.out.println(filter);

        treeManager.filter(tree, new Predicate<DepartmentNode>() {
            @Override
            public boolean test(DepartmentNode departmentNode) {
                return departmentNode.getName().equals("31") || departmentNode.getName().equals("23");
            }
        });
        System.out.println(tree);
    }
}
