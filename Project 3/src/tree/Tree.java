package tree;

import symbol_table.ScopeTree;

import java.util.ArrayList;
import java.util.List;

public class Tree {
    public Node root;

    public Tree() {

    }

    public void printTree(){
        print(this.root);
    }

    public void print(Node n){
        if(n == null){
            return;
        }else{
            for(Node lk : n.getLeftChildren()){
                print(lk);
            }
            System.out.println(n);
            for(Node rk : n.getRightChildren()){
                print(rk);
            }
        }
    }

    public void insertNode(Node n)       {
//        System.out.println("INSERT "  + n);
        if(this.root == null) {
            System.out.println("SETTING ROOT");
            this.root = n;
        }else{
            Node parent = findNode(root, n.getParentId());
             if(n.getSide() == 'l'){
                       parent.getLeftChildren().add(n);
                  n.setParent(parent);
            } else{
                parent.getRightChildren().add(n);
                n.setParent(parent);
            }
        }
    }

    public Node findNode(Node n, String id) {
//        System.out.println("FINDING: " + id + " Looking at: " + n.getId()     );
        if(n.getId().equals(id)){
            return n;
        }else {
            if(n.getLeftChildren().size() > 0){
                for(Node ns : n.getLeftChildren()) {
                    Node node = findNode(ns, id);
                    if(node != null){
                        return node;
                    }
                }
            }
            if(n.getRightChildren().size() > 0){
                for(Node ns : n.getRightChildren()) {
                    Node node = findNode(ns, id);
                    if(node != null){
                        return node;
                    }
                }

            }
        }
        return null;
    }

    public void executeNode(Node n){
        if(n == null){
            return;
        }
        if(n.getType() == Node.NodeType.EQUAL){
            String lVal = n.getLeftChildren().get(0).getValue();
            Node lValNode = n.getLeftChildren().get(0);
            String rVal = n.getRightChildren().get(0).getValue();
            Node rValNode = n.getRightChildren().get(0);
            lValNode.getSctNode().updateSymbol(lVal, rVal);

        }
        if(n.getType() == Node.NodeType.KPRINT){
            handlePrint(n.getLeftChildren().get(0));
        }
        else{
            for(Node node : n.getLeftChildren()){
                executeNode(node);
            }
            for(Node node : n.getRightChildren()){
                executeNode(node);
            }
        }
    }

    public void handlePrint(Node n){
        if(n.getType() == Node.NodeType.KPLUS){
            Node left = n.getLeftChildren().get(0);
            Node right = n.getRightChildren().get(0);
            int result = Integer.parseInt(left.getValue()) + Integer.parseInt(right.getValue());
            System.out.println("kprint node output: " + result);
        } else{
            handlePrint(n.getLeftChildren().get(0));
        }
    }
}
