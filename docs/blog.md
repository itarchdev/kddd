---
layout: default
title: Блог
nav_order: 3
---
# Новости проекта
{% for post in site.posts %}
### [{{ post.title }}]({{ post.url }})
*{{ post.date | date_to_string }}*
{% endfor %}
