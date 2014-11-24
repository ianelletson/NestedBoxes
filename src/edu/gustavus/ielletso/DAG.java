package edu.gustavus.ielletso;

import java.util.*;

class DAG {
    private final List<Node> nodes;
    private final Map<Node, Integer> distance;

    public DAG() {
        nodes = new ArrayList<Node>();
        distance = new HashMap<Node, Integer>();
    }

    public void addNode(int x, int y, int z) {
        Node newNode = new Node(x, y, z);
        for (Node node : nodes) {
            node.addEdge(newNode);
            newNode.addEdge(node);
        }
        nodes.add(newNode);
        distance.put(newNode, -1);
    }

    public int longestPath() {
        for (Node node : nodes)
            if (distance.get(node) == -1)
                dfs(node);
        int length = 0;
        for (Integer i : distance.values()) {
            if (i > length)
                length = i;
        }
        return length + 1; /* this '+1' accounts for not counting a
                              node in its own list of adjacent nodes */
    }

    private void dfs(Node v) {
        distance.put(v, 0);
        for (Node w : v.getAdjacentToNodes()) {
            if (distance.get(w) == -1) dfs(w);
            int max = Math.max(distance.get(v), (1 + distance.get(w)));
            distance.put(v, max);
        }
    }

    @Override public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Node node : nodes) {
            sb.append(node);
            for (Node in_node : node.getAdjacentToNodes()) sb.append(in_node);
            sb.append("\n");
        }
        return sb.toString();
    }

    private class Node {
        private final int x;
        private final int y;
        private final int z;
        private final List<Node> adjacentToNodes;

        private List<Node> getAdjacentToNodes() {
            return adjacentToNodes;
        }

        private Node(int x, int y, int z) {
            this.x = x;
            this.y = y;
            this.z = z;
            adjacentToNodes = new LinkedList<Node>();
        }

        private void addEdge(Node w) {
            if (fitsInto(w))
                adjacentToNodes.add(w);
        }

        private boolean fitsInto(Node w) {
            if ((x < w.x) && (y < w.y) && (z < w.z)) return true;
            if ((x < w.y) && (y < w.z) && (z < w.x)) return true;
            return (x < w.z) && (y < w.x) && (z < w.y);
        }

        @Override public String toString() {
            return String.format("(%d %d %d) ", x, y, z);
        }
    }
}
