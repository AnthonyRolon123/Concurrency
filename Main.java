import java.util.*;

class Main 
{
  public static void main(String[] args)   
  {
    int[] array = new int[200000];
    
    int[] firstQuarter = new int[50000];
    int[] secondQuarter = new int[50000];
    int[] thirdQuarter = new int[50000];
    int[] fourthQuarter = new int[50000];
    
    Random rn = new Random();
    int arraySize = array.length / 4;

    for(int i = 0; i < 200000; i++)
    {
      array[i] = rn.nextInt((10-1)+1)+1;
    }

    for(int i = 0; i < arraySize; i++)
    {
      firstQuarter[i] = array[i];
    }

    for(int i = 0; i < arraySize; i++)
    {
      secondQuarter[i] = array[i+(arraySize)];
    }

    for(int i = 0; i < arraySize; i++)
    {
      thirdQuarter[i] = array[i+(arraySize*2)];
    }

    for(int i = 0; i < arraySize; i++)
    {
      fourthQuarter[i] = array[i+(arraySize*3)];
    }

    parallel par1 = new parallel(firstQuarter);
    parallel par2 = new parallel(secondQuarter);
    parallel par3 = new parallel(thirdQuarter);
    parallel par4 = new parallel(fourthQuarter);
    
    normal nor = new normal(array);

    new Thread(par1).run();
    new Thread(par2).run();
    new Thread(par3).run();
    new Thread(par4).run();

    nor.run();

    int totalSumForParallel = par1.getSum() + par2.getSum() + par3.getSum() + par4.getSum();
    int totalSumForNormal = nor.getSum();

    long timeForParallel = par1.getTime() + par2.getTime() + par3.getTime() + par4.getTime();
    long timeForNormal = nor.getTime();

    System.out.println("sum is " + totalSumForParallel + " and time is " + timeForParallel + " - Parallel");
    System.out.println("sum is " + totalSumForNormal + " and time is " + timeForNormal + " - Normal");
  }
}

class parallel extends Thread
{
  public int[] array;
  public int sum = 0;
  public long time = 0;

  public parallel(int[] array)
  {
    this.array = array;
  }

  @Override
  public void run()
  {
    long startTime = System.currentTimeMillis();
    
    for(int i = 0; i < array.length; i++)
    {
      sum += array[i];
    }

    long endTime = System.currentTimeMillis();
    time = endTime - startTime;
  }

  public int getSum()
  {
    return sum;
  }

  public long getTime()
  {
    return time;
  }
}

class normal
{
  public int[] array;
  public int sum = 0;
  public long time = 0;
  
  public normal(int[] array)
  {
    this.array = array;
  }

  public void run()
  {
    long startTime = System.currentTimeMillis();

    for(int i = 0; i < array.length; i++)
    {
      sum += array[i];
    }

    long endTime = System.currentTimeMillis();
    time = endTime - startTime;
  }

  public int getSum()
  {
    return sum;
  }

  public long getTime()
  {
    return time;
  }
}