package main;

import subject.Subject;

/**
 * Observes objects to check if a collision is going to occur.
 */
public class CollisionHandler {

    RaccoonGame raccoonGame;

    public CollisionHandler(RaccoonGame raccoonGame) {
        this.raccoonGame = raccoonGame;
    }

    public int checkObject(Subject subject, boolean player) {
        int index = 999;

        for (int i = 0; i < raccoonGame.objects.length; i++) {
            if (raccoonGame.objects[i] != null) {
                //find subjects position
                subject.collidableArea.x = subject.x + subject.collidableArea.x;
                subject.collidableArea.y = subject.y + subject.collidableArea.y;

                //find item/objects position
                raccoonGame.objects[i].collidableArea.x = raccoonGame.objects[i].x + raccoonGame.objects[i].collidableArea.x;
                raccoonGame.objects[i].collidableArea.y = raccoonGame.objects[i].y + raccoonGame.objects[i].collidableArea.y;

                //check for overlap
                if (subject.direction == "up" || subject.direction == "down" || subject.direction == "left" || subject.direction == "right") {
                    switch (subject.direction) {
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

                    if (subject.collidableArea.intersects(raccoonGame.objects[i].collidableArea)) {
                        if (raccoonGame.objects[i].collidable) {
                            subject.collisionOn = true;
                        }
                        if (player) {
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


    public boolean checkEnemy(Subject subject, boolean player) {
        boolean collided = false;

        for (int i = 0; i < raccoonGame.enemyHandler.EnemyList.size(); i++) {
            if (raccoonGame.enemyHandler.EnemyList.get(i) != null) {
                //find subjects position
                subject.collidableArea.x = subject.x + subject.collidableArea.x;
                subject.collidableArea.y = subject.y + subject.collidableArea.y;

                //find item/objects position
                raccoonGame.enemyHandler.EnemyList.get(i).collidableArea.x = raccoonGame.enemyHandler.EnemyList.get(i).x + raccoonGame.enemyHandler.EnemyList.get(i).collidableArea.x;
                raccoonGame.enemyHandler.EnemyList.get(i).collidableArea.y = raccoonGame.enemyHandler.EnemyList.get(i).y + raccoonGame.enemyHandler.EnemyList.get(i).collidableArea.y;

                //check for overlap
                if (subject.direction == "up" || subject.direction == "down" || subject.direction == "left" || subject.direction == "right") {
                    switch (subject.direction) {
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

                    if (subject.collidableArea.intersects(raccoonGame.enemyHandler.EnemyList.get(i).collidableArea)) {
                        if (raccoonGame.enemyHandler.EnemyList.get(i).collidable) {
                            subject.collisionOn = true;
                        }
                        if (player) {
                            collided = true;
                        }
                    }
                }

                //reset position variables;
                subject.collidableArea.x = subject.collidableAreaX;
                subject.collidableArea.y = subject.collidableAreaY;

                raccoonGame.enemyHandler.EnemyList.get(i).collidableArea.x = raccoonGame.enemyHandler.EnemyList.get(i).collidableAreaX;
                raccoonGame.enemyHandler.EnemyList.get(i).collidableArea.y = raccoonGame.enemyHandler.EnemyList.get(i).collidableAreaY;

            }
        }

        return collided;
    }

}