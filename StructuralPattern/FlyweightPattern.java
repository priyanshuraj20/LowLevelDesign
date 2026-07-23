package StructuralPattern;


class Tree {

    private int x;
    private int y;

    // Repeated every object
    private String name;
    private String color;
    private String texture;

    public Tree(int x,
                int y,
                String name,
                String color,
                String texture){

        this.x = x;
        this.y = y;

        this.name = name;
        this.color = color;
        this.texture = texture;
    }

    public void draw(){

        System.out.println(
                "Drawing " + name +
                        " Tree at (" + x + "," + y + ")");
    }
}
class Forest {

    private List<Tree> trees =
            new ArrayList<>();


    public void plantTree(
            int x,
            int y,
            String name,
            String color,
            String texture){

        Tree tree =
                new Tree(
                        x,
                        y,
                        name,
                        color,
                        texture);

        trees.add(tree);
    }


    public void draw(){

        for(Tree tree : trees){
            tree.draw();
        }
    }
}
public class FlyweightPattern {


    private List<Tree> trees =
            new ArrayList<>();


    public void plantTree(
            int x,
            int y,
            String name,
            String color,
            String texture){

        Tree tree =
                new Tree(
                        x,
                        y,
                        name,
                        color,
                        texture);

        trees.add(tree);
    }


    public void draw(){

        for(Tree tree : trees){
            tree.draw();
        }
    }
}
//Problem
//
//Memory
//
//Tree 1
//
//x
//y
//Oak
//Green
//Rough
//
//------------------
//
//Tree 2
//
//x
//y
//Oak
//Green                  10 lakh baar store ho raha hai.


//Flyweight Solution
//
//Split data into
//
//Intrinsic State (Shared)
//
//Same for everyone.

//Extrinsic State (Unique)
//
//Different for every tree.

class TreeType {

    private String name;
    private String color;
    private String texture;


    public TreeType(
            String name,
            String color,
            String texture){

        this.name = name;
        this.color = color;
        this.texture = texture;
    }


    public void draw(int x,int y){

        System.out.println(
                "Drawing "
                        + name
                        + " tree at ("
                        + x
                        + ","
                        + y
                        + ")");
    }
}
class Tree {

    private int x;
    private int y;

    // Shared object
    private TreeType treeType;


    public Tree(
            int x,
            int y,
            TreeType treeType){

        this.x = x;
        this.y = y;

        this.treeType = treeType;
    }


    public void draw(){

        treeType.draw(x,y);
    }
}
import java.util.*;

class TreeFactory {

    private static Map<String,TreeType> treeTypes =
            new HashMap<>();


    public static TreeType getTreeType(
            String name,
            String color,
            String texture){

        String key =
                name + "-" + color + "-" + texture;


        // Already created?
        if(!treeTypes.containsKey(key)){

            treeTypes.put(
                    key,
                    new TreeType(
                            name,
                            color,
                            texture));
        }

        return treeTypes.get(key);
    }
}
class Forest {

    private List<Tree> trees =
            new ArrayList<>();


    public void plantTree(
            int x,
            int y,
            String name,
            String color,
            String texture){

        Tree tree =
                new Tree(
                        x,
                        y,
                        TreeFactory.getTreeType(
                                name,
                                color,
                                texture));

        trees.add(tree);
    }


    public void draw(){

        for(Tree tree : trees){

            tree.draw();
        }
    }
}
// Why Factory?
//
//Kyunki
//
//Factory hi decide karti hai
//
//"Naya object banana hai ya existing reuse karna hai."
//MEMORY NOW:
//TreeType
//
//Oak
//Green
//Rough
//
//▲
//│
//│
//├──────────────┐
//│              │
//│              │
//Tree1        Tree2
//
//x=10         x=40
//
//y=30         y=80
//
//same TreeType reference