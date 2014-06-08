<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html; charset=utf-8" language="java"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Privacy notice</title>
<link rel="stylesheet" type="text/css" href="css/pbi-web-ps-jawr.css" media="all" />
<link rel="stylesheet" type="text/css" href="css/pbi-web-ps-jawr-print.css" media="print" />
</head>

<body>
	<div class="flex-col lt-col"
		style="margin-left: auto; margin-right: auto">

		<div class="table-module">
			<p style="float: right">${rev_date}</p>
			<div class="consumer-privacy-notice-skin">
				<h2 class="h2-fsd-red">${fname}</h2>
				<div class="content-section left-heading">

					<div class="content-row">
						<h3 class="cpn-cell cpn-heading-left" style="height: 27px;">FACTS</h3>
						<div class="cpn-cell cpn-details-right" style="height: 27px;">
							<p>
								<strong>WHAT DOES ${fname} DO WITH YOUR
									PERSONAL INFORMATION?</strong>
							</p>
						</div>
						<div class="clearboth"></div>
					</div>
					<div class="content-row">
						<div class="cpn-cell cpn-heading-left" style="height: 81px;">Why?</div>
						<div class="cpn-cell cpn-details-right" style="height: 81px;">
							<p>Financial companies choose how they share your personal
								information. Under federal law, that means personally
								identifiable information. Federal law gives consumers the right
								to limit some but not all sharing. Federal law also requires us
								to tell you how we collect, share, and protect your personal
								information. Please read this notice carefully to understand
								what we do.</p>
						</div>
						<div class="clearboth"></div>
					</div>
					<div class="content-row">
						<div class="cpn-cell cpn-heading-left" style="height: 153px;">What?</div>
						<div class="cpn-cell cpn-details-right" style="height: 153px;">
							<p>The types of personal information we collect and share
								depend on the product or service you have with us.</p>
							<p>This information can include:</p>
							<ul class="gray-sq-bullet">
								<li>Social Security number and ${what_coll["0"]}</li>
								<li>${what_coll["1"]} and ${what_coll["2"]}</li>
								<li>${what_coll["3"]} and ${what_coll["4"]}</li>
							</ul>
							<c:choose>
								<c:when test="${not offer_opt_out}">
									<p>When you are no longer our customer, we continue to share
										your information as described in this notice.</p>
								</c:when>
							</c:choose>
						</div>
						<div class="clearboth"></div>
					</div>
					<div class="content-row">
						<div class="cpn-cell cpn-heading-left" style="height: 81px;">How?</div>
						<div class="cpn-cell cpn-details-right" style="height: 81px;">
							<p>All financial companies need to share customers’ personal
								information to run their everyday business. In the section
								below, we list the reasons financial companies can share their
								customers’ personal information; the reasons ${fname }
								chooses to share; and whether you can limit this sharing.</p>
						</div>
						<div class="clearboth"></div>
					</div>
				</div>
				<div class="content-section ">

					<table border="0"
						summary="This 3-column table provides information about the reasons why Bank of America can share your personal information.">
						<thead>
							<tr>
								<th scope="col">Reasons we can share your personal
									information</th>
								<th scope="col">Does ${fname} share?</th>
								<th scope="col">Can you limit this sharing?</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td><strong>For our everyday business purposes </strong>—
									such as to process your transactions, maintain your account(s),
									respond to court orders and legal investigations, or report to
									credit bureaus</td>
								<td>${share_eday}</td>
								<td>${limit_eday}</td>
							</tr>
							<tr>
								<td><strong>For our marketing purposes </strong>— to offer our products and services to
									you</td>
								<td>${share_mar}</td>
								<td>${limit_mar}</td>
							</tr>
							<tr>
								<td><strong>For joint marketing with other financial companies</strong></td>
								<td>${share_joint}</td>
								<td>${limit_joint}</td>
							</tr>
							<tr>
								<td><strong>For our affiliates’ everyday business purposes </strong>— information about your transactions and experiences</td>
								<td>${share_aff}</td>
								<td>${limit_aff}</td>
							</tr>
							<tr>
								<td><strong>For our affiliates’ everyday business purposes </strong>— information about your creditworthiness</td>
								<td>${share_credit}</td>
								<td>${limit_credit}</td>
							</tr>
							<c:choose>
								<c:when test=${not omit_share_aff_mar}>
									<tr>
										<td><strong>For our affiliates’ everyday business purposes </strong>— Information about your creditworthiness</td>
										<td>${share_aff_mar}</td>
										<td>${limit_aff_mar}</td>
									</tr>
								</c:when>
							</c:choose>
							<tr>
								<td><strong>For nonaffiliates to market to you </strong>— for all credit card accounts</td>
								<td>${share_non_aff_mar}</td>
								<td>${limit_non_aff_mar}</td>
							</tr>
						</tbody>
					</table>
				</div>
				<div class="content-section left-heading">

				<c:choose>
					<c:when test="${offer_opt_out}">
						<div class="content-row">
							<h3 class="cpn-cell cpn-heading-left" style="height: 194px;">To
								limit our sharing</h3>
							<div class="cpn-cell cpn-details-right" style="height: 194px;">
								<ul class="gray-sq-bullet">
								<c:choose>
									<c:when test="${phone_opt}">
									<li>Call${opt_phone} - our menu will prompt you
										through your choices</li></c:when>
										
									<c:when test="${online_opt }">
									<li>Visit us online: <a title="privacy_overview"
										name="privacy_overview_1"
										href="${opt_web}"
										target="_self">${opt_web}</a>
									</li></c:when>
									
									<c:when test="${mail_opt }">
									<li>Mail the form below</li></c:when>
								</c:choose>
								</ul>
								<p class="ptop-5">
									<strong>Please note:</strong><br /> If you are a new customer,
									we can begin sharing your information ${opt_num_days} days from the date we
									sent this notice. When you are no longer our customer, we
									continue to share your information as described in this notice.
									However, you can contact us at any time to limit our sharing.
								</p>
							</div>
							<div class="clearboth"></div>
						</div>
					</c:when>
				</c:choose>

					<div class="content-row">
						<h3 class="cpn-cell cpn-heading-left" style="height: 27px;">Questions?</h3>
						<div class="cpn-cell cpn-details-right" style="height: 27px;">
							<p>
								Call ${opt_phone} or go to ${opt_web}
							</p>
						</div>
						<div class="clearboth"></div>
					</div>
				</div>
				
				<c:choose>
					<c:when test="${mail_opt_out}">
						<br/>
						<!-- mail-in form here -->
						<br/>
					</c:when>
				</c:choose>
				
				<div class="content-section ">
					<h3>Who we are</h3>
					<div class="content-row">
						<div class="cpn-cell cpn-heading">Who is providing this
							notice?</div>
						<div class="cpn-cell cpn-details">
							<p>${who_we_are}</p>
						</div>
						<div class="clearboth"></div>
					</div>
				</div>
				<div class="content-section ">
					<h3>What we do</h3>
					<div class="content-row">
						<div class="cpn-cell cpn-heading">How does ${fname}
							protect my personal information?</div>
						<div class="cpn-cell cpn-details">
							<p>
								To protect your personal information from unauthorized access
								and use, we use security measures that comply with federal law.
								These measures include computer safeguards and secured files and
								buildings.
								<br />
								${protect}
							</p>
						</div>
						<div class="clearboth"></div>
					</div>
					<div class="content-row">
						<div class="cpn-cell cpn-heading">How does ${fname}
							collect my personal information?</div>
						<div class="cpn-cell cpn-details">
							<p>We collect your personal information, for example, when
								you:</p>
							<ul class="gray-sq-bullet">
								<li>${how_coll["0"]} or ${how_coll["1"]}</li>
								<li>${how_coll["2"]} or ${how_coll["3"]}</li>
								<li>${how_coll["4"]}</li>
							</ul>
							<p>${other_src_info}</p>
						</div>
						<div class="clearboth"></div>
					</div>
					<div class="content-row">
						<div class="cpn-cell cpn-heading">Why can’t I limit all
							sharing?</div>
						<div class="cpn-cell cpn-details">
							<p>Federal law gives you the right to limit only</p>
							<ul class="gray-sq-bullet">
								<li>sharing affiliates' everyday business purposes—information
									about your creditworthiness</li>
								<li>affiliates from using your information to market to you
								</li>
								<li>sharing for nonaffiliates to market to you</li>
							</ul>
							<p>
								State laws and individual companies may give you
								more rights to limit sharing. ${see_below}
							</p>
						</div>
						<div class="clearboth"></div>
					</div>
					<c:choose>
						<c:when test="${offer_opt_out }">
							<div class="content-row">
								<div class="cpn-cell cpn-heading">What happens when I limit
									sharing for an account I hold jointly with someone else?</div>
								<div class="cpn-cell cpn-details">
									<p>Your choices will apply to you alone unless you tell us
										otherwise.</p>
								</div>
								<div class="clearboth"></div>
							</div>
						</c:when>
					</c:choose>
				</div>
				<div class="content-section ">
					<h3>Definitions</h3>
					<div class="content-row">
						<div class="cpn-cell cpn-heading">Affiliates</div>
						<div class="cpn-cell cpn-details">
							<p>Companies related by common ownership or control. They can
								be Financial and nonfinancial companies.</p>
							<ul class="gray-sq-bullet">
								<li>${aff}</li>
							</ul>
						</div>
						<div class="clearboth"></div>
					</div>
					<div class="content-row">
						<div class="cpn-cell cpn-heading">Nonaffiliates</div>
						<div class="cpn-cell cpn-details">
							<p>Companies not related by common ownership or control. They
								can be Financial and nonfinancial companies.</p>
							<ul class="gray-sq-bullet">
								<li>${naff}</li>
							</ul>
						</div>
						<div class="clearboth"></div>
					</div>
					<div class="content-row">
						<div class="cpn-cell cpn-heading">Joint Marketing</div>
						<div class="cpn-cell cpn-details">
							<p>A formal agreement between nonaffiliated financial
								companies that together market financial products or services to
								you.</p>
							<ul class="gray-sq-bullet">
								<li>${jmar}</li>
							</ul>
						</div>
						<div class="clearboth"></div>
					</div>
				</div>
				<div class="content-section cpn-one-col">
					<h3>Other important information</h3>
					<p>${other_info}</p>
				</div>
				
				<div class="content-section left-rev"></div>
			</div>
		</div>
	</div>

</body>
</html>