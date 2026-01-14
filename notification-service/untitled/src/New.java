import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class New {

    class Solution {
        public List<List<Integer>> fourSum(int[] nums, int target) {
            Arrays.sort(nums);

            List<List<Integer>> output = new ArrayList<>();

            int mid = 0;
            int left = 0;
            int right = 0;



// ith pointer = fixed
            for(int i = 0 ; i < nums.length-3; i++){


// skipping duplicate for i pointer
                if(i==0  || nums[i]!=nums[i-1] ){

                    left = i+1; // making left pointer fixed;

                    // skipping duplicate for left pointer
                    if(nums[left]!=nums[left-1]){
                        mid= left+1;
                        right = nums.length-1;

                        int fixedTotal = nums[i]+ nums[left];

                        int required = target- fixedTotal;

// starting iteration of mid and right pointers
                        while(mid < right ){
                            int currentTotal = nums[mid]+nums[right]; //WHYYYYYYYYYYYYYYYYY DSAAAAAAAAAAAAAAAAAAA

// incrementing mid pointer and skipping duplicate;
                            if(currentTotal < required ){
                                while(mid< right && nums[mid]== nums[mid+1]){
                                    mid++;
                                }
                                mid++;

                            }

// decrementing right pointer and skipping duplicates
                            else if(currentTotal> required){

                                while(right > mid && nums[right] == nums[right-1]){
                                    right--;

                                }
                                right--;
                            }

                            else if(currentTotal == required ){
                                List<Integer> newOne = new ArrayList<>();
                                newOne.add(nums[i]);
                                newOne.add(nums[left]);
                                newOne.add(nums[mid]);
                                newOne.add(nums[right]);
                                output.add(newOne);


// skipping duplicated for mid and right pointer


                                while( left < right && nums[left]== nums[left+1]){
                                    left++;

                                }

                                while( right > left && nums[right]== nums[right-1]){
                                    right--;
                                }
                                left++;
                                right++;


                            }


                        }
                    }
                }
            }


            return output;


        }
    }

}
