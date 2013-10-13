package dark.mining.common.privateutils;

import java.util.ArrayList;
import java.util.List;

public class TripleContainer<A, B, C>
{

    public ArrayList<A> aList;
    public ArrayList<B> bList;
    public ArrayList<C> cList;

    public void put(A input1, B input2, C input3)
    {
        aList.add(input1);
        bList.add(input2);
        cList.add(input3);
    }

    public List getAList()
    {
        return aList;
    }

    public List getBList()
    {
        return bList;
    }

    public List getCList()
    {
        return cList;
    }
}