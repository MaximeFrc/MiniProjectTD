


public class Path {
	
	public Case [] path;
	
	public Path (int numPath, Case[][] tabCase) {
		switch (numPath) {
			case 1 :
			path = new Case [8];
			path[0] = tabCase [2][0];
			path[1] = tabCase [2][6];
			path[2] = tabCase [20][6];
			path[3] = tabCase [20][10];
			path[4] = tabCase [2][10];
			path[5] = tabCase [2][14];
			path[6] = tabCase [20][14];
			path[7] = tabCase [20][17];
			break;
			case 2 :
			path = new Case [10];
			path[0] = tabCase [0][2];
			path[1] = tabCase [19][2];
			path[2] = tabCase [19][15];
			path[3] = tabCase [3][15];
			path[4] = tabCase [3][5];
			path[5] = tabCase [16][5];
			path[6] = tabCase [16][12];
			path[7] = tabCase [6][12];
			path[8] = tabCase [6][8];
			path[9] = tabCase [13][8];
			break;
			case 3 :
			path = new Case [14];
			path[0] = tabCase [6][0];
			path[1] = tabCase [6][7];
			path[2] = tabCase [2][7];
			path[3] = tabCase [2][3];
			path[4] = tabCase [19][3];
			path[5] = tabCase [19][1];
			path[6] = tabCase [17][1];
			path[7] = tabCase [17][16];
			path[8] = tabCase [19][16];
			path[9] = tabCase [19][14];
			path[10] = tabCase [2][14];
			path[11] = tabCase [2][10];
			path[12] = tabCase [6][10];
			path[13] = tabCase [6][17];
			break;
			case 4 :
			path = new Case [39];
			path[0] = tabCase [0][7];
			path[1] = tabCase [8][7];
			path[2] = tabCase [8][4];
			path[3] = tabCase [3][4];
			path[4] = tabCase [3][1];
			path[5] = tabCase [20][1];
			//path[6] = tabCase [20][1];
			path[6] = tabCase [14][1];
			path[7] = tabCase [14][7];
			path[8] = tabCase [20][7];
			path[9] = tabCase [20][16];
			path[10] = tabCase [20][15];
			path[11] = tabCase [19][15];
			path[12] = tabCase [19][14];
			path[13] = tabCase [18][14];
			path[14] = tabCase [18][13];
			path[15] = tabCase [17][13];
			path[16] = tabCase [17][12];
			path[17] = tabCase [16][12];
			path[18] = tabCase [16][11];
			path[19] = tabCase [15][11];
			path[20] = tabCase [15][10];
			path[21] = tabCase [14][10];
			path[22] = tabCase [14][9];
			path[23] = tabCase [13][9];
			path[24] = tabCase [13][16];
			path[25] = tabCase [9][16];
			path[26] = tabCase [9][13];
			path[27] = tabCase [8][13];
			path[28] = tabCase [8][11];
			path[29] = tabCase [7][11];
			path[30] = tabCase [7][9];
			path[31] = tabCase [4][9];
			path[32] = tabCase [4][11];
			path[33] = tabCase [3][11];
			path[34] = tabCase [3][13];
			path[35] = tabCase [7][13];
			path[36] = tabCase [2][13];
			//path[37] = tabCase [2][14];
			path[37] = tabCase [2][16];
			path[38] = tabCase [0][16];
			break;
		}
		
		path[0].setPath();
		for(int i=0; i<(path.length-1); i++) {
			if (path[i].getX() == path [i+1].getX()) {
				for (int j=0; j<tabCase.length; j++) {
					for (int k=0; k<tabCase[0].length; k++) {
						if (tabCase[j][k].getX() == path[i].getX()) {
							if (((tabCase[j][k].getY()>path[i].getY())&&(tabCase[j][k].getY()<=path[i+1].getY()))||((tabCase[j][k].getY()<path[i].getY())&&(tabCase[j][k].getY()>=path[i+1].getY()))) {
								tabCase[j][k].setPath();
							}
						}
					}
				}
			} else {
				for (int j=0; j<tabCase.length; j++) {
					for (int k=0; k<tabCase[0].length; k++) {
						if (tabCase[j][k].getY() == path[i].getY()) {
							if (((tabCase[j][k].getX()>path[i].getX())&&(tabCase[j][k].getX()<=path[i+1].getX()))||((tabCase[j][k].getX()<path[i].getX())&&(tabCase[j][k].getX()>=path[i+1].getX()))) {
								tabCase[j][k].setPath();
							}
						}
					}
				}
			}
		}
	}
}

