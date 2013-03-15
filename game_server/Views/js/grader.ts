class Grader{
	computedScore: int;
	assignmentNum: int;
	scorer: string;
	student: string;


	constructor(assignmentNum: int, scorer: string, student: string){	
		this.assignmentNum = assignmentNum;
		this.scorer = scorer;
		this.student = student;
	}
}