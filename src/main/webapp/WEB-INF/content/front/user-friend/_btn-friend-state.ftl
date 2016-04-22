<#switch friendStatus>
<#case 'REQUESTED'>
<a href="${url('/user-friend/delete-ajax/'+user.id?c)}" class="ajax-get">リクエストを取り消す</a>
<#case 'FRIEND'>
<a href="${url('/user-friend/delete-ajax/'+user.id?c)}" class="ajax-get">友達を取り消す</a>
<#case 'NONE'>
<#default>
<a href="${url('/user-friend/create-ajax/'+user.id?c)}" class="ajax-get">友達になる</a>
</#switch>