<%@ page
  contentType="text/html; charset=UTF-8"
  import="javax.servlet.*"
  import="javax.servlet.http.*"
  import="java.io.*"
  import="java.util.*"
  import="org.apache.nutch.mapReduce.*"
%>
<%
  String jobid = request.getParameter("jobid");
  JobTracker tracker = JobTracker.getTracker();
  JobTracker.JobInProgress job = (JobTracker.JobInProgress) tracker.getJob(jobid);
  JobProfile profile = (job != null) ? (job.getProfile()) : null;
  JobStatus status = (job != null) ? (job.getStatus()) : null;
%>

<html>
<title>Nutch MapReduce Job Details</title>
<body>
<h1>Job '<%=jobid%>'</h1>

<b>Job File:</b> <%=profile.getJobFile()%><br>
<b>Start time:</b> <%= new Date(job.getStartTime())%><br>
<%
  if (status.getRunState() == JobStatus.RUNNING) {
    out.print("The job is still running.<br>\n");
  } else if (status.getRunState() == JobStatus.SUCCEEDED) {
    out.print("<b>The job completed at:</b> " + new Date(job.getFinishTime()) + "<br>\n");
  } else if (status.getRunState() == JobStatus.FAILED) {
    out.print("<b>The job failed at:</b> " + new Date(job.getFinishTime()) + "<br>\n");
  }
%>
<hr>

<h2>Map Tasks</h2>
  <center>
  <table border=2 cellpadding="5" cellspacing="2">
  <tr><td align="center" colspan="4">Map Tasks</td></tr>

  <%

  %>
  </table>
  </center>
<hr>


<h2>Reduce Tasks</h2>


<hr>
<a href="/jobtracker.jsp">Go back to JobTracker</a><br>
<a href="http://www.nutch.org/">Nutch</a>, 2005.<br>
</body>
</html>