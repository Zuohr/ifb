<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html; charset=utf-8"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Online opt-out</title>
<link rel="stylesheet" type="text/css" href="pbi-web-ps-jawr.css"
	media="all">
<style type="text/css">
#child0 {
	position: absolute;
	visibility: hidden;
	top: 120px;
	z-index: 7
}

#child1 {
	position: absolute;
	visibility: hidden;
	top: 120px;
	z-index: 8
}

#child2 {
	position: absolute;
	visibility: hidden;
	top: 120px;
	z-index: 9
}

#child3 {
	position: absolute;
	visibility: hidden;
	top: 120px;
	z-index: 10
}

#child4 {
	position: absolute;
	visibility: hidden;
	top: 120px;
	z-index: 11
}

#child5 {
	position: absolute;
	visibility: hidden;
	top: 120px;
	z-index: 12
}
</style>
</head>

<body>
	<div class="flex-col lt-col"
		style="margin-left: auto; margin-right: auto; width: 800px;">



		<div class="table-module">
			<div class="consumer-privacy-notice-skin">
				<h2 class="h2-fsd-red">&nbsp;</h2>

				<div>
					<h3>Online OPT-OUT Form</h3>
					<td class="contentcopy"><table border="0" cellspacing="0"
							cellpadding="0" class="contentcopy">
							<tbody>
								<tr>
									<td class="contentcopy">
									<c:forEach var="msg" items="${mail_opt_options}">
									<div align="left">
											<input name="share_internal" id="share_internal"
												type="checkbox" value="choice_internal" />${msg }<br /> <br />
									</div>
									</c:forEach>
									</td>
								</tr>
								<tr>
									<td class="contentcopy"><div align="left">
											Please do not solicit me for additional products and services
											through:<br />
										</div></td>
								</tr>
								<tr>
									<td>&nbsp;&nbsp;&nbsp;
										<div align="left">
											<input name="share_external_phone" id="share_external_phone" type="checkbox" value="choice_telemarketing" />cookies, or<br /> 
											<input name="share_external_mail" id="share_external_mail" type="checkbox" value="choice_mail" />tracking by websites<br />
										</div>
									</td>
								</tr>
							</tbody>
						</table></td>
					</tr>
					<tr>
						<td valign="top" align="left" class="contentcopy">All items
							in <span class="contentcopybold">bold</span> are required:
						</td>
					</tr>
					<tr>
						<td valign="top" align="left"><div align="left">
								<p>
									<span class="contentcopybold"> <strong>Last
											Name</strong></span><br /> <input name="Last_Name" id="Last_Name"
										type="text" maxlength="40" size="40" />
								</p>
								<p>
									<br />
								</p>
							</div></td>
					</tr>
					<tr>
						<td valign="top" align="left"><div align="left">
								<p>
									<span class="contentcopybold"><strong>First
											Name</strong></span><br /> <input name="First_Name" id="First_Name"
										type="text" maxlength="40" size="40" />
								</p>
								<p>
									<br />
								</p>
							</div></td>
					</tr>
					<tr>
						<td valign="top" align="left"><div align="left">
								<p>
									<span class="contentcopybold"><strong>Last 4
											digits of your Social Security Number</strong></span><br /> <input
										name="SSN" id="SSN" type="text" maxlength="4" size="4"
										onchange="checkSSN(SSN)" />
								</p>
								<p>
									<br />
								</p>
							</div></td>
					</tr>
					<tr>
						<td valign="top" align="left" class="contentcopy"><div
								align="left">
								<p>
									E-Mail Address<br /> <input name="Email" id="Email"
										type="text" maxlength="30" size="30" />
								</p>
								<p>
									<br />
								</p>
							</div></td>
					</tr>
					<tr>
						<td valign="top" align="left" class="contentcopy"><div
								align="left">
								<p>
									Last Name (joint owner) <span class="contentcopybold"><strong>On
											Joint accounts, you must complete the name and last four
											digits of the Social Security number if a joint owner wishes
											to make an election.</strong></span>
								</p>
								<p>
									<input name="Joint_Last_Name" id="Joint_Last_Name" type="text"
										maxlength="40" size="40" />
								</p>
								<p>
									<br />
								</p>
							</div></td>
					</tr>
					<tr>
						<td valign="top" align="left" class="contentcopy"><div
								align="left">
								<p>
									First Name<br /> <input name="Joint_First_Name"
										id="Joint_First_Name" type="text" maxlength="40" size="40" />
								</p>
								<p>
									<br />
								</p>
							</div></td>
					</tr>
					<tr>
						<td valign="top" align="left" class="contentcopy"><div
								align="left">
								<p>
									Last 4 digits of the joint owner Social Security Number<br />
									<input name="Joint_SSN" id="Joint_SSN" type="text"
										maxlength="4" size="4" onchange="checkSSN(Joint_SSN)" />
								</p>
								<p>
									<br />
								</p>
							</div></td>
					</tr>
					<tr>
						<td valign="top" align="left"><div align="left">
								<p>
									<span class="contentcopybold"><strong>Street
											Address</strong></span><br /> <input name="Street" id="Street" type="text"
										maxlength="40" size="40" />
								</p>
								<p>
									<br />
								</p>
							</div></td>
					</tr>
					<tr>
						<td valign="top" align="left" class="contentcopy"><div
								align="left">
								<p>
									Apt #<br /> <input name="Apartment" id="Apartment" type="text"
										maxlength="15" size="15" />
								</p>
								<p>
									<br />
								</p>
							</div></td>
					</tr>
					<tr>
						<td><table border="0" cellspacing="0" cellpadding="0"
								class="contentcopy">
								<tbody>
									<tr>
										<td width="50%"><div align="left">
												<span class="contentcopybold"><strong>City</strong></span>
											</div></td>
										<td width="50%"><div align="left">
												<span class="contentcopybold"><strong>State</strong></span>
											</div></td>
									</tr>
									<tr>
										<td width="50%"><div align="left">
												<input name="City" id="City" type="text" maxlength="30"
													size="30" />
											</div></td>
										<td width="50%"><div align="left">
												<select class="contentcopy" name="STATE_ID">
													<option value="">Select state...</option>
													<option value="AK">Alaska</option>
													<option value="AL">Alabama</option>
													<option value="AR">Arkansas</option>
													<option value="AS">American Samoa</option>
													<option value="AZ">Arizona</option>
													<option value="CA">California</option>
													<option value="CO">Colorado</option>
													<option value="CT">Connecticut</option>
													<option value="DC">Washington D.C.</option>
													<option value="DE">Delaware</option>
													<option value="FL">Florida</option>
													<option value="FM">Federated States of Micronesia</option>
													<option value="GA">Georgia</option>
													<option value="GU">Guam</option>
													<option value="HI">Hawaii</option>
													<option value="IA">Iowa</option>
													<option value="ID">Idaho</option>
													<option value="IL">Illinois</option>
													<option value="IN">Indiana</option>
													<option value="KS">Kansas</option>
													<option value="KY">Kentucky</option>
													<option value="LA">Louisiana</option>
													<option value="MA">Massachusetts</option>
													<option value="MD">Maryland</option>
													<option value="ME">Maine</option>
													<option value="MH">Marshall Islands</option>
													<option value="MI">Michigan</option>
													<option value="MN">Minnesota</option>
													<option value="MO">Missouri</option>
													<option value="MP">Northern Mariana Islands</option>
													<option value="MS">Mississippi</option>
													<option value="MT">Montana</option>
													<option value="NC">North Carolina</option>
													<option value="ND">North Dakota</option>
													<option value="NE">Nebraska</option>
													<option value="NH">New Hampshire</option>
													<option value="NJ">New Jersey</option>
													<option value="NM">New Mexico</option>
													<option value="NV">Nevada</option>
													<option value="NY">New York</option>
													<option value="OH">Ohio</option>
													<option value="OK">Oklahoma</option>
													<option value="OR">Oregon</option>
													<option value="PA">Pennsylvania</option>
													<option value="PR">Puerto Rico</option>
													<option value="PW">Palau</option>
													<option value="RI">Rhode Island</option>
													<option value="SC">South Carolina</option>
													<option value="SD">South Dakota</option>
													<option value="TN">Tennessee</option>
													<option value="TX">Texas</option>
													<option value="UT">Utah</option>
													<option value="VA">Virginia</option>
													<option value="VI">Virgin Islands</option>
													<option value="VT">Vermont</option>
													<option value="WA">Washington</option>
													<option value="WI">Wisconsin</option>
													<option value="WV">West Virginia</option>
													<option value="WY">Wyoming</option>
												</select>
											</div></td>
									</tr>
								</tbody>
							</table></td>
					</tr>
					<tr></tr>
					<tr>
						<td><table border="0" cellspacing="0" cellpadding="0"
								class="contentcopy">
								<tbody>
									<tr>
										<td width="50%"><div align="left">
												<span class="contentcopybold"><strong>Zip
														Code</strong></span>&nbsp;&nbsp;<span class="contentsubcopy">(99999-9999)</span>
											</div></td>
										<td width="50%" class="contentcopy"><div align="left">
												Telephone&nbsp;&nbsp;<span class="contentsubcopy">(999-999-9999)</span>
											</div></td>
									</tr>
									<tr>
										<td width="50%"><div align="left">
												<input name="ZipCode" id="ZipCode" type="text"
													maxlength="10" />
											</div></td>
										<td width="50%"><div align="left">
												<input name="Phone" id="Phone" type="text" />
											</div></td>
									</tr>
								</tbody>
							</table></td>


						<td colspan="2" valign="BOTTOM" align="left">
							<div align="left">
								<input type="submit" name="action_submit" value="Submit"
									style="width: 100px; height: 40px;">
									&nbsp;&nbsp;&nbsp;&nbsp; <input type="reset"
									name="btnAction_Clear" value="  Clear "
									style="width:100px; height:40px;">
                            
							</div>
                            </td>
      	
				</div>
  </div>
</div>
</div>

</body>
</html>