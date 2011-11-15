package faultfinder.views;



public class StatementInfo 
{
	public String FileName;
	public int LineNumber;
	public String StatementText;
	public double Rank;
	
	public static StatementInfo ImportStatementInfo(String line)
	{
		if( line.isEmpty() )
			return null;
		String[] data = line.split("#!.!#");
		if( data.length != 4)
			return null;
		
		StatementInfo info = new StatementInfo();
		info.FileName = data[0];
		info.LineNumber = Integer.parseInt(data[1]);
		info.StatementText = data[2];
		info.Rank = Double.parseDouble(data[3]);
		
		return info;
	}
	

}
