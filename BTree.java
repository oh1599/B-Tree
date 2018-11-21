package btree;

import java.util.Stack;

public class BTree
{
	public Node root;//��Ʈ ���
	public int m;//����
	
	//Ʈ���� ������ȸ ������ִ� �Լ�
	public void printInorder()
	{
		inorder(root);
		System.out.println();
	}
	
	//�ϳ��� ����� Ű���� ��� ��� ���� �������� ������ ���� �ִ� �Լ��̴�.
	//���� ���� Ű���� ���� ��ȯ���ش�.
	public Node firstHalf(Node node)
	{
		int mid = node.numKey/2;
		Node n = new Node();
		for(int i=0;i<mid;i++)
		{
			n.data[n.numKey++] = node.data[i];
		}
		return n;
	}
	
	//�ϳ��� ����� Ű���� ��� ��� ���� �������� ������ ���� �ִ� �Լ��̴�.
	//���� ���� Ű���� ���� ��ȯ���ش�.
	public Node secondHalf(Node node)
	{
		int mid = node.numKey/2;
		Node n = new Node();
		if(node.numKey%2 == 1)
		{
			for(int i=0;i<mid;i++)
			{
				n.data[n.numKey++] = node.data[i+mid+1];
			}
		}
		else
		{
			for(int i=0;i<mid;i++)
			{
				n.data[n.numKey++] = node.data[i+mid];
			}
		}
		return n;
	}
	
	//B-Tree�� ������ȸ�� ���ִ� �Լ�
	public void inorder(Node node)
	{
		 for (int i=0;i<node.numKey;i++)
		 {
		     if (!node.isLeafNode())
		     {
		    	 inorder(node.child[i]);
			 }
			 System.out.print(node.data[i] + " ");
		 }
	     if (!node.isLeafNode())
	     {
	         inorder(node.child[node.numKey]);
	     }
	}
	
	public void sortData(Node node)
	{
		for(int i=0;i<node.numKey-1;i++)
		{
			for(int j=0;j<node.numKey-1;j++)
			{
				if(node.data[j] > node.data[j+1])
				{
					int tmp = node.data[j];
					node.data[j] = node.data[j+1];
					node.data[j+1] = tmp;
				}
			}
		}
	}
	
	//B-Tree�� �����ڷ� ���ڿ� �ִ� ���� ������ �Ѵ�.
	public BTree(int m)
	{
		this.m = m;
		System.out.printf("m = %d �� ��,\n", m);
	}
	
	//B-Tree�� ���ο� Ű�� �����ϴ� �Լ��̴�.
	public void insertBT(int newKey)
	{
		Stack<Node> stack = new Stack<>();
		Node x = root;
		Node y = null;
		Node temp = null;
		
		if(root == null)
		{
			Node node = new Node(newKey);
			root = node;
			return;
		}
		//stack�� ��带 ��� ����
		do
		{
			int i = 0;//�� ��� �����ִ� key�� �����ϴ� �ε�����
			while(i<=x.numKey && newKey > x.data[i])	i++;
			if(i<=x.numKey && newKey == x.data[i])
			{
				return;
			}
			stack.push(x);
			x = x.child[i];
		}while(x!=null);//stack�� leaf�������� ��� ��� ����

		boolean finished = false;//������ ���� �����ϴ� flag ����
		do
		{
			x = stack.peek();
			if(x.numKey<m-1)	//overflow �߻� ���� -> ���� ��忡 ����
			{
				x.insertKey(newKey);
				finished = true;
				sortData(x);
				
			}
			else	//overflow �߻� ���� -> split
			{
				temp = x;
				
				temp.insertKey(newKey);
				sortData(temp);
				
				newKey = temp.getMidValue(); //��� ���� ���ο� Ű ������ ����
				
				//��带 ����� �������� �� �ڷ� ����
				x = firstHalf(temp);//�� �κ�
				y = secondHalf(temp);//�� �κ�
			}
			if(!stack.empty())
			{
				stack.pop();
			}
			if(stack.empty())//���� ������� Ʈ���� ���� ����
			{
				Node t = new Node(newKey);
				t.child[0] = x;
				t.child[1] = y;
				finished = true;
			}
		}while(!finished);
	}
}