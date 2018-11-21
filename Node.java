package btree;

import java.util.Arrays;

public class Node
{
	public int numKey;//한 노드 내에 있는 데이터의 수
	public int[] data = new int[1000];//노드안의 데이터를 담는 배열
	public Node[] child = new Node[1000];//자식 노드 배열
	
	//키 값을 노드 내에 삽입 하는 함수
	public void insertKey(int data)
	{
		this.data[numKey] = data;
		numKey++;
	}
	
	//노드내에 있는 중간 키값을 반환하는 함수
	public int getMidValue()
	{
		int mid = numKey/2;	
		return data[mid];
	}
	
	//해당 노드가 leaf노드인지 아닌지 판단해주는 함수
	boolean isLeafNode()
	{
		boolean isLeaf = true;
		for(int i=0;i<numKey;i++)
		{
			if(child[i] != null)
			{
				isLeaf = false;
				break;
			}
		}
		return isLeaf;
	}
	
	//기본 생성자로서 키의 수를 0으로 설정하였다.
	public Node()
	{
		this.numKey = 0;
	}	
	
	//인자에 있는 정수 값을 노드의 첫번째 키로 저장하는 생성자.
	public Node(int data)
	{
		this.data[0] = data;
		this.numKey = 1;
	}
}