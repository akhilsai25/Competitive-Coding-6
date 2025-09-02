// This solution uses back tracking to generate the combinations and get to a valid arrangement. We can use a boolean flags to store the check for selected or unselected elements
class Solution {
    boolean[] flags;
    int count = 0;
    public int countArrangement(int n) {
        flags = new boolean[n];
        arrangement(n, 1);
        return count;
    }

    private void arrangement(int n, int index) {
        if(index>n) {
            count++;
            return;
        }

        for(int i=1;i<=n;i++) {
            if(!flags[i-1] && validate(i, index)) {
                flags[i-1] = true;
                arrangement(n, index+1);
                flags[i-1] = false;
            }
        }
    }

    private boolean validate(int n, int index) {
        return n%index == 0 || index%n == 0;
    }
}

