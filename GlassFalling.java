/**
 * Glass Falling
 */
public class GlassFalling {

  // Do not change the parameters!
  public int glassFallingRecur(int floors, int sheets) {

    if (floors <= 1 || sheets == 1) {
      return floors;
    } else {
      int minFalls = Integer.MAX_VALUE, minComparison;
      for (int i = 1; i <= floors; i++) {
        minComparison = Math.max(glassFallingRecur(i - 1, sheets - 1), glassFallingRecur(floors - i, sheets));
        if (minComparison < minFalls) {
          minFalls = minComparison;
        }
      }
      return minFalls + 1;
    }

  }

  // Optional:
  // Pick whatever parameters you want to, just make sure to return an int.
  public int glassFallingMemoized() {
    // Fill in here and change the return
    return 0;
  }

  // Do not change the parameters!

  // Do not change the parameters!
  public int glassFallingBottomUp(int floors, int sheets) {

    // the table represents the solutions to glassFalling(floor,sheets)
    int glassFalling[][] = new int[sheets + 1][floors + 1];

    // initialize the second row to 0 through n
    for (int i = 0; i <= floors; i++) {
      glassFalling[1][i] = i;
    }

    for (int i = 2; i <= sheets; i++) {
      for (int j = 1; j <= floors; j++) {

        // at most it could be the highest floor, work to find the smallest floor
        int minimum = floors;

        for (int z = 1; z <= j; z++) { // one attempt + the fewest possible number of attempts
          // which is yielded by glassFalling(i,j-z) and glassFalling(i-1,j-1) as per
          // recursive formulation
          minimum = Math.min(minimum, (1 + Math.max(glassFalling[i][j - z], glassFalling[i - 1][z - 1])));
        }

        // set best and continue upwards
        glassFalling[i][j] = minimum;
      }
    }

    return glassFalling[sheets][floors];

  }

  public static void main(String args[]) {
    GlassFalling gf = new GlassFalling();

    // Do not touch the below lines of code, and make sure
    // in your final turned-in copy, these are the only things printed
    int minTrials1Recur = gf.glassFallingRecur(27, 2);
    int minTrials1Bottom = gf.glassFallingBottomUp(27, 2);
    //int minTrials2Recur = gf.glassFallingRecur(100, 3);
    int minTrials2Bottom = gf.glassFallingBottomUp(100, 3);
    System.out.println(minTrials1Recur + " " + minTrials1Bottom);
    System.out.println("*MEMOIZED RECURSIVE NOT IMPLEMENTED*" + " " + minTrials2Bottom);
  }
}
