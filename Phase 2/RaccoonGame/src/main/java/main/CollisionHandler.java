package main;

import subject.Subject;

public class CollisionHandler {

    RaccoonGame raccoonGame;

    public CollisionHandler(RaccoonGame raccoonGame) {
        this.raccoonGame = raccoonGame;
    }

    public void checkBlock(Subject subject) {
        //int varialbes to store which block subject is hitting
        int blockNum1 = 0, blockNum2 = 0;

        //find 4 corner coordinates of subject's collidable area
        int subjectLeftX = subject.x + subject.collidableArea.x;
        int subjectRightX = subject.x + subject.collidableArea.width;
        int subjectTopY = subject.y + subject.collidableArea.y;
        int subjectBottomY = subject.y + subject.collidableArea.height;

        //check which blocks the subject is in
        int leftCol = subjectLeftX/raccoonGame.blockSize;
        int rightCol = subjectRightX/raccoonGame.blockSize;
        int topRow = subjectTopY/raccoonGame.blockSize;
        int bottomRow = subjectBottomY/ raccoonGame.blockSize;

        if(subject.direction == "up" || subject.direction == "down" || subject.direction == "left" || subject.direction == "right") {
            switch(subject.direction) {
                case "up":
                    topRow = (subjectTopY - subject.speed) / raccoonGame.blockSize;
                    blockNum1 = raccoonGame.mapManager.mapBlockArr[leftCol][topRow];
                    blockNum2 = raccoonGame.mapManager.mapBlockArr[rightCol][topRow];
                    break;
                case "down":
                    bottomRow = (subjectBottomY + subject.speed) / raccoonGame.blockSize;
                    blockNum1 = raccoonGame.mapManager.mapBlockArr[leftCol][bottomRow];
                    blockNum2 = raccoonGame.mapManager.mapBlockArr[rightCol][bottomRow];
                    break;
                case "left":
                    leftCol = (subjectLeftX - subject.speed) / raccoonGame.blockSize;
                    blockNum1 = raccoonGame.mapManager.mapBlockArr[leftCol][topRow];
                    blockNum2 = raccoonGame.mapManager.mapBlockArr[leftCol][bottomRow];

                    break;
                case "right":
                    rightCol = (subjectRightX + subject.speed) / raccoonGame.blockSize;
                    blockNum1 = raccoonGame.mapManager.mapBlockArr[rightCol][topRow];
                    blockNum2 = raccoonGame.mapManager.mapBlockArr[rightCol][bottomRow];
                    break;
            }

            if(raccoonGame.mapManager.blocks[blockNum1].collidable || raccoonGame.mapManager.blocks[blockNum2].collidable) {
                subject.collisionOn = true;
            }
        }

    }

    public int checkObject(Subject subject, boolean player) {
        int index = 999;

        for(int i = 0; i < raccoonGame.objects.length; i++) {
            if(raccoonGame.objects[i] != null) {
                //find subjects position
                subject.collidableArea.x = subject.x + subject.collidableArea.x;
                subject.collidableArea.y = subject.y + subject.collidableArea.y;

                //find item/objects position
                raccoonGame.objects[i].collidableArea.x = raccoonGame.objects[i].x + raccoonGame.objects[i].collidableArea.x;
                raccoonGame.objects[i].collidableArea.y = raccoonGame.objects[i].y + raccoonGame.objects[i].collidableArea.y;

                //check for overlap
                if(subject.direction == "up" || subject.direction == "down" || subject.direction == "left" || subject.direction == "right") {
                    switch(subject.direction) {
                        case "up":
                            subject.collidableArea.y -= subject.speed;
                            break;
                        case "down":
                            subject.collidableArea.y += subject.speed;
                            break;
                        case "left":
                            subject.collidableArea.x -= subject.speed;
                            break;
                        case "right":
                            subject.collidableArea.x += subject.speed;
                            break;

                    }

                    if(subject.collidableArea.intersects(raccoonGame.objects[i].collidableArea)) {
                        if(raccoonGame.objects[i].collidable) {
                            subject.collisionOn = true;
                        }
                        if(player) {
                            index = i;
                        }
                    }
                }

                //reset position variables;
                subject.collidableArea.x = subject.collidableAreaX;
                subject.collidableArea.y = subject.collidableAreaY;

                raccoonGame.objects[i].collidableArea.x = raccoonGame.objects[i].collidableAreaX;
                raccoonGame.objects[i].collidableArea.y = raccoonGame.objects[i].collidableAreaY;

            }
        }

        return index;
    }
}
