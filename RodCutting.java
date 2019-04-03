import java.util.Arrays;

/**
 * Rod cutting problem described in Chapter 15 of textbook
 */
public class RodCutting {

  // Do not change the parameters!
  public int rodCuttingRecur(int rodLength, int[] lengthPrices) {

    // the problem cannot be computed on negative inputs, smallest case yields 0
    if (rodLength <= 0) {
      return 0;
    }

    // compare all other lengths against first index.
    int maxCuts = lengthPrices[0];

    for (int i = 0; i < rodLength; i++) {

      // expands to max(maxCuts, (max (maxCuts (max (maxCuts (.... 0))))))
      maxCuts = Math.max(maxCuts, lengthPrices[i] + rodCuttingRecur(rodLength - i - 1, lengthPrices));

    }

    // return once resolved
    return maxCuts;

  }

  // Do not change the parameters!
  public int rodCuttingBottomUp(int rodLength, int[] lengthPrices) {

    int savedMaxCuts[] = new int[rodLength + 1];

    // equivalent to 0 base case in recursive formulation
    savedMaxCuts[0] = 0;

    // solving upwards until rodLength, start at one as base case already solved.
    for (int i = 1; i <= rodLength; i++) {

      int maxCuts = lengthPrices[0];
      for (int z = 0; z < i; z++) {
        // lookup saved
        maxCuts = Math.max(maxCuts, lengthPrices[z] + savedMaxCuts[i - z - 1]);
        // save result after resolved
        savedMaxCuts[i] = maxCuts;

      }
    }
    // return once solved up to this point
    return savedMaxCuts[rodLength];
  }

  public static void main(String args[]) {
    RodCutting rc = new RodCutting();

    // In your turned in copy, do not touch the below lines of code.
    // Make sure below is your only output.
    int length1 = 7;
    int[] prices1 = { 1, 4, 7, 3, 19, 5, 12 };
    int length2 = 14;
    int[] prices2 = { 2, 5, 1, 6, 11, 15, 17, 12, 13, 9, 10, 22, 18, 26 };
    int maxSell1Recur = rc.rodCuttingRecur(length1, prices1);
    int maxSell1Bottom = rc.rodCuttingBottomUp(length1, prices1);
    int maxSell2Recur = rc.rodCuttingRecur(length2, prices2);
    int maxSell2Bottom = rc.rodCuttingBottomUp(length2, prices2);
    System.out.println(maxSell1Recur + " " + maxSell1Bottom);
    System.out.println(maxSell2Recur + " " + maxSell2Bottom);
  }
}
