public class EquilibriumArray {
    public static void main(String[] args) {
//        int[] arr = {1, 3, 5, 2, 2};
        int[] arr = {9, 1, 3, 3, 4, 1, 2};

//        int equilibriumIndex = equilibrium(arr);
//        System.out.println("Equilibrium index: " + equilibriumIndex);

        int equilibriumIndex = findEquilibriumIndex(arr);
        System.out.println(equilibriumIndex);
    }

    private static int equilibrium(int[] arr) {
        int length = arr.length;
        int equilibrium = (length - 1) / 2;
        int leftSum = 0;
        int rightSum = 0;
        do {
            int leftIndex = 0;
            int rightIndex = length - 1;
            leftSum = 0;
            rightSum = 0;
            while (leftIndex < equilibrium || rightIndex > equilibrium) {
                if (leftIndex < equilibrium) {
                    leftSum += arr[leftIndex];
                }
                if (rightIndex > equilibrium) {
                    rightSum += arr[rightIndex];
                }
                leftIndex++;
                rightIndex--;
            }
            if (leftSum < rightSum) {
                equilibrium++;
            } else if (leftSum > rightSum) {
                equilibrium--;
            }
        } while (leftSum != rightSum);
        return equilibrium;
    }

    public static int findEquilibriumIndex(int[] arr) {
        int totalSum = 0;
        for (int num : arr) {
            totalSum += num;
        }

        int leftSum = 0;
        for (int i = 0; i < arr.length; i++) {
            totalSum -= arr[i]; // totalSum is now the right sum for index i

            if (leftSum == totalSum) {
                return i; // Found the equilibrium index
            }

            leftSum += arr[i];
        }

        return -1; // No equilibrium index found
    }

}