package services;

import models.FootballClub;

import java.util.Comparator;

public class SortComparator implements Comparator<FootballClub> {
    @Override
    public int compare(FootballClub o1, FootballClub o2) {

        int pointsCompare = o1.getPoints()-(o2.getPoints());
        int goalDifferenceCompare = o1.getGoalDifference()-(o2.getGoalDifference());

        // if the points are same comparator will sort according to the goal differece
        if (pointsCompare==0) {
            return ((goalDifferenceCompare == 0) ? pointsCompare : goalDifferenceCompare);
        } else {
            return pointsCompare;
        }
        /////////////////////////////////

        //////////////////////////

//        int point = 0;
//         if(o1.getPoints()>o2.getPoints()){
//             point= -1;
//             return point;
//
//         }else if(o1.getPoints()<o2.getPoints()){
//             point= 1;
//             return point;
//
//         }else {
//             if(o1.getGoalDifference()>o2.getGoalDifference()){
//                 point=-1;
//                 return point;
//             }
//             if(o1.getGoalDifference()<o2.getGoalDifference()){
//                 point=1;
//                 return point;
//             }
//         }
//         return point;
    }

}
