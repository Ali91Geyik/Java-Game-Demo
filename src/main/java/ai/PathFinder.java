package ai;

import entity.Entity;
import main.GamePanel;

import java.util.ArrayList;

public class PathFinder {
    GamePanel gp;
    Node[][] node;
    public ArrayList<Node> openList = new ArrayList<>();
    public ArrayList<Node> pathList = new ArrayList<>();

    Node startNode, goalNode, currentNode;
    boolean goalReached = false;
    int step=0;

    public PathFinder(GamePanel gp){
        this.gp=gp;
        instantiateNodes();
    }
    public void instantiateNodes(){
        node = new Node[gp.maxWorldCol][gp.maxWorldRow];
        int col=0;
        int row=0;

        while (col<gp.maxWorldCol&& row<gp.maxWorldRow){
            node[col][row]=new Node(col,row);
            col++;
            if(col==gp.maxWorldCol){
                col=0;
                row++;
            }
        }
    }
    public void resetNodes(){
        int col=0;
        int row=0;
        while (col<gp.maxWorldCol&&row< gp.maxWorldRow){
            //reset open, checked and solid state
            node[col][row].open=false;
            node[col][row].checked=false;
            node[col][row].solid=false;
            col++;
            if(col==gp.maxWorldCol){
                col=0;
                row++;
            }

        }
    //Reset other settings
        openList.clear();
        pathList.clear();
        goalReached=false;
        step=0;


    }
    public void setNodes(int startCol, int startRow, int goalCol, int goalRow, Entity entity){
    resetNodes();

    //SET START AND GOAL NODE
        startNode=node[startCol][startRow];
        currentNode=startNode;
        goalNode=node[goalCol][goalRow];
        openList.add(currentNode);

        int col=0;
        int row=0;
        while (col<gp.maxWorldCol && row<gp.maxWorldRow){
            //SET SOLİD NODE
            //CHECK TİLES
            int tileNum= gp.tileM.mapTileNum[gp.currentMap][col][row];
            if (gp.tileM.tile[tileNum].collision==true){
                node[col][row].solid=true;
            }
            //SET COST
            getCost(node[col][row]);
            col++;
            if(col== gp.maxWorldCol){
                col=0;
                row++;
            }
        }
    }
    public void getCost(Node node){
        //G COST
        int xDistance= Math.abs(node.col- startNode.col);
        int yDistance= Math.abs(node.row- startNode.row);
        node.gCost=xDistance+yDistance;
        //H Cost
        xDistance=Math.abs(node.col- goalNode.col);
        yDistance=Math.abs(node.row-goalNode.row);
        node.hCost = xDistance+yDistance;
        //F Cost
        node.fCost= node.gCost+node.hCost;

    }
    public boolean search(){
        while (goalReached==false&&step<500){
            int col = currentNode.col;
            int row = currentNode.row;

            //CHECK THE CURRENT NODE
            currentNode.checked=true;
            openList.remove(currentNode);

            //OPEN THE UP NODE
            if (row-1>=0){
                openNode(node[col][row-1]);
            }

            //OPEN THE DOWN NODE
            if(row+1< gp.maxWorldRow){
                openNode(node[col][row+1]);
            }

            //OPEN THE LEFT NODE
            if (col-1>=0){
                openNode(node[col-1][row]);
            }

            //OPEN THE RIGHT NODE
            if (col+1< gp.maxWorldCol){
                openNode(node[col+1][row]);
            }
            //FIND THE BEST NODE
            int bestNodeIndex=0;
            int bestNodeFCost=999;
            for (int i=0; i<openList.size();i++){
                //CHECK THE FCOST OF this node
                if (openList.get(i).fCost<bestNodeFCost){
                    bestNodeIndex=i;
                    bestNodeFCost=openList.get(i).fCost;
                }
                // if f cost is equal check the gcost
                else if (openList.get(i).fCost== bestNodeFCost){
                    if(openList.get(i).gCost<openList.get(bestNodeIndex).gCost){
                        bestNodeIndex=i;

                    }
                }
            }
            //if there is no node in the openlist stop the loop
            if(openList.size()==0){
                break;
            }
            //After the loop, openList[bestNodeIndex] is the next step(=currentNode)
            currentNode=openList.get(bestNodeIndex);

            if (currentNode==goalNode){
                goalReached=true;
                trackThePath();
            }
            step++;
        }
        return goalReached;
    }
    public void openNode(Node node){
        if (node.open==false&& node.checked==false&& node.solid==false){
            node.open=true;
            node.parent=currentNode;
            openList.add(node);
        }
    }
    public void trackThePath(){
        Node current= goalNode;
        while (current!= startNode){
            pathList.add(0,current);
            current=current.parent;
        }
    }
}

