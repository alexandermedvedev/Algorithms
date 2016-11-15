import edu.princeton.cs.algs4.WeightedQuickUnionUF;
import edu.princeton.cs.algs4.StdOut;

public class Percolation {
    private int[][] grid; //BLOCKED - 0; OPEN - 1
    private int size;
    private WeightedQuickUnionUF uf;

    public Percolation(int n) {                // create n-by-n grid, with all sites blocked
        if (n <= 0)
            throw new java.lang.IllegalArgumentException();
        else {
            size = n;
            grid = new int[n][n];
            for (int i = 0; i < n; i++)
                for (int j = 0; j < n; j++)
                    grid[i][j] = 0;

            uf = new WeightedQuickUnionUF(n*n + 2);
        }
    }

    public void open(int row, int col) {      // open site (row, col) if it is not open already
        if (row < 1 || row > size || col < 1 || col > size)
            throw new java.lang.IndexOutOfBoundsException();

        if (!isOpen(row, col)){            
            row = row - 1;
            col = col - 1;
            grid[row][col] = 1;
            
            if (row == 0)
                uf.union(0, coord(row,col));
            if (row == size - 1)
                uf.union(size*size + 1, coord(row,col));
            if (row + 1 < size && grid[row+1][col] == 1)
                uf.union(coord(row,col), coord(row+1, col));
            if (row - 1 >= 0 && grid[row-1][col] == 1)
                uf.union(coord(row,col), coord(row-1, col));    
            if (col + 1 < size && grid[row][col+1] == 1)
                uf.union(coord(row,col), coord(row, col+1));
            if (col - 1 >= 0 && grid[row][col-1] == 1)
                uf.union(coord(row,col), coord(row, col-1));    
        }
    }

    public boolean isOpen(int row, int col) {  // is site (row, col) open?
        if (row < 1 || row > size || col < 1 || col > size)
            throw new java.lang.IndexOutOfBoundsException();
        
        return (grid[row-1][col-1] == 1);
    }

    public boolean isFull(int row, int col) {  // is site (row, col) full?
        if (row < 1 || row > size || col < 1 || col > size)
            throw new java.lang.IndexOutOfBoundsException();
        
        return (uf.connected(0,coord(row - 1, col - 1)));
    }

    public boolean percolates() {              // does the system percolate?
        return uf.connected(0, size*size + 1);
    }
    
    private int coord(int i, int j) {
//        if (i < 0 || i > size || j < 0 || j > size)
  //          throw new IndexOutOfBoundsException("Illegal parameter value.");
        return ((i*size) + j) + 1;  
    }
    /*
    public static void main(String[] args) {
        Percolation p = new Percolation(6);
       p.open(1, 6);
StdOut.println(p.isOpen(1,6));
p.open(2, 6);
StdOut.println(p.isOpen(2,6));
p.open(3, 6);
StdOut.println(p.isOpen(3,6));
p.open(4, 6);
StdOut.println(p.isOpen(4,6));
p.open(5, 6);
StdOut.println(p.isOpen(5,6));
p.open(5, 5);
StdOut.println(p.isOpen(5,5));
p.open(4, 4);
StdOut.println(p.isOpen(4,4));
p.open(3, 4);
StdOut.println(p.isOpen(3,4));
p.open(2, 4);
StdOut.println(p.isOpen(2,4));
p.open(2, 3);
StdOut.println(p.isOpen(2,3));
p.open(2, 2);
StdOut.println(p.isOpen(2,2));
p.open(2, 1);
StdOut.println(p.isOpen(2,1));
p.open(3, 1);
StdOut.println(p.isOpen(3,1));
p.open(4, 1);
StdOut.println(p.isOpen(4,1));
p.open(5, 1);
StdOut.println(p.isOpen(5,1));
p.open(5, 2);
StdOut.println(p.isOpen(5,2));
p.open(6, 2);
StdOut.println(p.isOpen(6,2));
p.open(5, 4);
StdOut.println(p.isOpen(5,4));
        StdOut.println("12345");
    }
    */
}
    