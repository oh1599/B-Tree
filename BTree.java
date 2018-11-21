package btree;

import java.util.Stack;

public class BTree
{
	public Node root;//루트 노드
	public int m;//차수
	
	//트리의 중위순회 출력해주는 함수
	public void printInorder()
	{
		inorder(root);
		System.out.println();
	}
	
	//하나의 노드의 키값을 가운데 노드 값을 기준으로 반으로 나눠 주는 함수이다.
	//앞의 절반 키값을 노드로 반환해준다.
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
	
	//하나의 노드의 키값을 가운데 노드 값을 기준으로 반으로 나눠 주는 함수이다.
	//뒤의 절반 키값을 노드로 반환해준다.
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
	
	//B-Tree의 중위순회를 해주는 함수
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
	
	//B-Tree의 생성자로 인자에 있는 값을 차수로 한다.
	public BTree(int m)
	{
		this.m = m;
		System.out.printf("m = %d 일 때,\n", m);
	}
	
	//B-Tree에 새로운 키를 삽입하는 함수이다.
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
		//stack에 노드를 담는 과정
		do
		{
			int i = 0;//한 노드 내에있는 key에 접근하는 인덱스값
			while(i<=x.numKey && newKey > x.data[i])	i++;
			if(i<=x.numKey && newKey == x.data[i])
			{
				return;
			}
			stack.push(x);
			x = x.child[i];
		}while(x!=null);//stack에 leaf노드까지의 모든 노드 저장

		boolean finished = false;//삽입의 끝을 결정하는 flag 변수
		do
		{
			x = stack.peek();
			if(x.numKey<m-1)	//overflow 발생 안함 -> 리프 노드에 삽입
			{
				x.insertKey(newKey);
				finished = true;
				sortData(x);
				
			}
			else	//overflow 발생 했음 -> split
			{
				temp = x;
				
				temp.insertKey(newKey);
				sortData(temp);
				
				newKey = temp.getMidValue(); //가운데 값을 새로운 키 값으로 설정
				
				//노드를 가운데를 기준으로 앞 뒤로 분할
				x = firstHalf(temp);//앞 부분
				y = secondHalf(temp);//뒷 부분
			}
			if(!stack.empty())
			{
				stack.pop();
			}
			if(stack.empty())//스택 비어있음 트리의 레벨 증가
			{
				Node t = new Node(newKey);
				t.child[0] = x;
				t.child[1] = y;
				finished = true;
			}
		}while(!finished);
	}
}