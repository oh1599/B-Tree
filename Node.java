package btree;

import java.util.Arrays;

public class Node
{
	public int numKey;//�� ��� ���� �ִ� �������� ��
	public int[] data = new int[1000];//������ �����͸� ��� �迭
	public Node[] child = new Node[1000];//�ڽ� ��� �迭
	
	//Ű ���� ��� ���� ���� �ϴ� �Լ�
	public void insertKey(int data)
	{
		this.data[numKey] = data;
		numKey++;
	}
	
	//��峻�� �ִ� �߰� Ű���� ��ȯ�ϴ� �Լ�
	public int getMidValue()
	{
		int mid = numKey/2;	
		return data[mid];
	}
	
	//�ش� ��尡 leaf������� �ƴ��� �Ǵ����ִ� �Լ�
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
	
	//�⺻ �����ڷμ� Ű�� ���� 0���� �����Ͽ���.
	public Node()
	{
		this.numKey = 0;
	}	
	
	//���ڿ� �ִ� ���� ���� ����� ù��° Ű�� �����ϴ� ������.
	public Node(int data)
	{
		this.data[0] = data;
		this.numKey = 1;
	}
}