package uebung7;

public class HomogeneousPair <S> extends HeterogeneousPair<S, S>
{

	HomogeneousPair <S> local;
	public HomogeneousPair(S left, S right)
	{
		super(left, right);
		local = new HomogeneousPair<>(left, right);
	}

}
