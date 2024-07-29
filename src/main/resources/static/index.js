$(document).ready(() => {
	const list = [
		{name: "Strudy01", url: "/s1/"},
		{name: "Strudy02", url: "/s2/"},
		{name: "Strudy03", url: "/s3/"}
	];
	$("ul").empty();
	$.each(list, (index, item) => $("ul").append(`<li class="text-center">${item.name}</li>`));
	$("li").on("click", (e) => location.href = list[$("li").index(e.target)].url);
});